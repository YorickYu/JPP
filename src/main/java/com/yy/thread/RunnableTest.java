package com.yy.thread;

public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println(this.getClass());
    }

    public static void main(String[] args) {
        // 只有run方法调用
        RunnableTest runnableTest = new RunnableTest();
        runnableTest.run();

        // 用Thread包装
        Thread thread = new Thread(new RunnableTest());
        thread.run();
        thread.start();

    }
}
