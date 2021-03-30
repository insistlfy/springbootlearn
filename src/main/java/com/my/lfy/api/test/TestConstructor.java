package com.my.lfy.api.test;

/**
 * TestConstructor
 * 测试构造器与代码块的执行顺序
 *
 * @author lfy
 * @date 2021/3/30
 **/
public class TestConstructor {

    public static void main(String[] args) {

//        Aa aa = new Aa();
        Bb bb = new Bb();
        System.out.println("=================================");
    }
}

class Aa {

    private int a;

    static {
        System.out.println("Aa static block ...");
    }

    {
        System.out.println("Aa block ...");
    }

    public Aa() {
        System.out.println("Aa NoArgs constructor...");
    }

    public Aa(int a) {
        this.a = a;
        System.out.println("Aa constructor...");
    }
}

class Bb extends Aa {

    private int b;

    static {
        System.out.println("Bb static block ...");
    }

    {
        System.out.println("Bb  block ...");
    }

    public Bb() {
        System.out.println("Bb NoArgs constructor...");
    }

    public Bb(int b) {
        this.b = b;
        System.out.println("Bb constructor...");
    }
}

