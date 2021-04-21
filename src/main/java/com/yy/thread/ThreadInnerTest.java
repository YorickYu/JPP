package com.yy.thread;

public class ThreadInnerTest {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            }).start();
        }).start();
    }
}
