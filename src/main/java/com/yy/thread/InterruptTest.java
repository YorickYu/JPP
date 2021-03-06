package com.yy.thread;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // false -> true
                // Thread.interrupted()重置为false
                while (!Thread.interrupted()) {
                    // do something
                }
            }
        });
        System.out.println(thread.isInterrupted()); // false
        thread.start();
        Thread.sleep(5000);
        thread.interrupt(); // 中断 thread
        Thread.sleep(100);
        System.out.println(thread.isInterrupted()); // false
    }
}
