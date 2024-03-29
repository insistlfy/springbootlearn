package com.my.lfy.utils;


import java.util.Arrays;
import java.util.Scanner;

/**
 * Compute
 *
 * @author lfy
 * @date 2021/5/14
 **/
public class Compute {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        String[] inOrderArrays = strToArrays(str);
        System.out.println(Arrays.toString(inOrderArrays));
        String[] postOrderArrays = toPostOrder(inOrderArrays);
        System.out.println(Arrays.toString(inOrderArrays));
        Double result = toCompute(postOrderArrays);
        System.out.printf("%.3f", result);
    }

    /**
     * 将字符串分割成操作数和操作符的字符串数组
     *
     * @param str String
     * @return String[]
     */
    public static String[] strToArrays(String str) {
        int strLength = str.length();
        int beginIndex = 0;
        int endIndex = 0;
        String[] Arrays = new String[strLength];
        int arraysIndex = 0;

        for (int i = 0; i < strLength; i++) {
            if (str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '(' || str.charAt(i) == ')') {
                endIndex = i - 1;
                if (beginIndex <= endIndex) {
                    Arrays[arraysIndex] = str.substring(beginIndex, endIndex + 1);
                    Arrays[arraysIndex + 1] = String.valueOf(str.charAt(i));
                    arraysIndex += 2;
                    beginIndex = i + 1;
                } else {
                    Arrays[arraysIndex] = String.valueOf(str.charAt(i));
                    arraysIndex += 1;
                    beginIndex = i + 1;
                }
            }
        }
        Arrays[arraysIndex] = str.substring(beginIndex, str.length());
        String[] Arrays2 = new String[arraysIndex + 1];
        for (int i = 0; i < arraysIndex + 1; i++) {
            Arrays2[i] = Arrays[i];
        }
        return Arrays2;
    }

    /**
     * 将中缀表达式转为后缀表达式,返回的是字符串数组
     *
     * @param arrays String[]
     * @return String[]
     */
    public static String[] toPostOrder(String[] arrays) {
        /*规则：
         *1，运算数直接输出
         *2，左括号压入堆栈
         *3，右括号 将栈顶的运算符弹出并输出，括号出栈不输出
         *4，运算符：
         *    若优先级大于栈顶运算符，压入栈
         *    若优先级小于或等于栈顶运算符，栈顶运算符弹出并输出，
         *       继续和新栈顶比较，直到比栈顶运算符优先级大，将它压入栈
         *5，对象处理完毕后，将栈中运算符弹出并输出
         */
        //创建了一个操作符的栈
        MyStack operStack = new MyStack();
        int arraysLength = arrays.length;
        //输出后的字符数组
        String[] arrays2 = new String[arraysLength];
        int tempIndex = 0;

        //将字符串数组遍历
        for (int i = 0; i < arraysLength; i++) {
            //操作符入栈
            if (isOper(arrays[i])) {
                //栈为空时直接入栈
                if (operStack.isEmpty()) {
                    operStack.push(arrays[i]);
                } else {
                    //操作符为"("时直接入栈
                    if (arrays[i].equals("(")) {
                        operStack.push(arrays[i]);
                    }
                    //操作符为")"时栈顶出栈并输出，直到遇到"(", "("出栈,")"不入栈
                    else if (arrays[i].equals(")")) {
                        while (operStack.peek().equals("(") == false) {
                            arrays2[tempIndex] = operStack.pop();
                            tempIndex += 1;
                        }
                        operStack.pop();//"("出栈
                    }
                    //其他操作符需要比较与栈顶的优先级
                    else {
                        //栈顶是"(", 直接入栈
                        if (operStack.peek().equals("(")) {
                            operStack.push(arrays[i]);
                        } else {
                            //优先级高，直接入栈
                            if (getPriority(arrays[i].charAt(0)) > getPriority(operStack.peek().charAt(0))
                                    && operStack.isEmpty() == false) {
                                operStack.push(arrays[i]);
                            }
                            //优先级低或者相等，栈顶出栈并输出，直到优先级比栈顶高
                            else {
                                while (getPriority(arrays[i].charAt(0)) <= getPriority(operStack.peek().charAt(0))
                                        && operStack.isEmpty() == false) {
                                    arrays2[tempIndex] = operStack.pop();
                                    tempIndex += 1;
                                    //若栈顶全部弹出，则直接入栈
                                    if (operStack.isEmpty()) {
                                        operStack.push(arrays[i]);
                                        break;
                                    }
                                }
                                if (getPriority(arrays[i].charAt(0)) > getPriority(operStack.peek().charAt(0))) {
                                    operStack.push(arrays[i]);
                                }
                            }
                        }
                    }
                }
            }
            //操作数直接添加到 字符串数组2
            else if (isNum(arrays[i])) {
                arrays2[tempIndex] = arrays[i];
                tempIndex += 1;
            } else {
            }
        }
        while (!operStack.isEmpty()) {
            arrays2[tempIndex] = operStack.pop();
            tempIndex += 1;
        }
        String[] arrays3 = new String[tempIndex];
        for (int i = 0; i < tempIndex; i++) {
            arrays3[i] = arrays2[i];
        }
        return arrays3;
    }

    /**
     * 得到操作符的优先级
     *
     * @param c char
     * @return int
     */
    public static int getPriority(char c) {
        if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 999;
        }
    }

    /**
     * 由后缀表达式计算得值
     *
     * @param arrays String[]
     * @return double
     */
    public static double toCompute(String[] arrays) {
        /*规则：
         *中缀表达式不用比较优先级
         *将运算数入栈，每读到一个运算符
         *就弹出栈顶的两个运算数，运算完毕后将结果压入栈
         */
        //创建了一个操作数的栈
        MyStack numStack = new MyStack();
        int arraysLength = arrays.length;
        //遍历后缀表达式的字符串数组
        for (int i = 0; i < arraysLength; i++) {
            if (isNum(arrays[i])) {
                numStack.push(arrays[i]);
            } else if (isOper(arrays[i])) {
                Double num2 = Double.parseDouble(numStack.pop());
                Double num1 = Double.parseDouble(numStack.pop());
                if (arrays[i].equals("+")) {
                    Double result = num1 + num2;
                    numStack.push(result.toString());
                } else if (arrays[i].equals("-")) {
                    Double result = num1 - num2;
                    numStack.push(result.toString());
                } else if (arrays[i].equals("*")) {
                    Double result = num1 * num2;
                    numStack.push(result.toString());
                } else if (arrays[i].equals("/")) {
                    Double result = num1 / num2;
                    numStack.push(result.toString());
                } else {
                }
            } else {
            }
        }
        Double result = Double.parseDouble(numStack.pop());
        return result;
    }

    /**
     * 判断该字符串是否为操作符
     *
     * @param str String
     * @return boolean
     */
    public static boolean isOper(String str) {
        if (str.equals("*") || str.equals("/") ||
                str.equals("+") || str.equals("-") ||
                str.equals("(") || str.equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断该字符串是否为操作数
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNum(String str) {
        if (str.equals("*") || str.equals("/") ||
                str.equals("+") || str.equals("-") ||
                str.equals("(") || str.equals(")")) {
            return false;
        } else {
            return true;
        }
    }
}
