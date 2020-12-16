package com.yy.thread;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        System.out.println(this.getName());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        threadTest.run();
        threadTest.start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
