package com.my.lfy.api.thread;

/**
 * TestThreadLocal
 *
 * @author lfy
 * @date 2020/8/9
 **/
public class TestThreadLocal {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        TestThreadLocal testThreadLocal = new TestThreadLocal();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                testThreadLocal.setContext(Thread.currentThread().getName() + "的数据");
                System.out.println("------------------------------");
                String context = testThreadLocal.getContext();
                System.out.println(Thread.currentThread().getName() + ":" + context);
            }).start();
        }
    }


    public String getContext() {
        return threadLocal.get();
    }

    public void setContext(String context) {
        threadLocal.set(context);
    }
}


