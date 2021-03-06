package com.yy.thread;

import com.sun.xml.internal.ws.util.CompletedFuture;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class YYExecutor {

    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static class MyThread2 implements Runnable {

        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 1
////        Thread myThread = new MyThread1();
////        myThread.start();
////        Thread thread = new Thread();
////
////        // 2
////        ThreadLocal<Integer> threadLocal = new ThreadLocal();
////        threadLocal.set(11);
////        Integer integer = threadLocal.get();
////        CompletableFuture.runAsync(() -> {
////        });

        // 3
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                System.out.println("testThread当前线程组名字：" +
//                        Thread.currentThread().getThreadGroup().getName());
//                System.out.println("testThread线程名字：" +
//                        Thread.currentThread().getName());
//            }).start();
//        }
//        new Thread(new ThreadGroup("t1"), ()-> {
//
//        }, "thread1").start();
//
//        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());

        //4
//        Object lock = new Object();
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    p(lock);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "T1");
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    p(lock);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "T2");
//        t1.start();
//        Thread.sleep(100);
//        t2.start();


        // 5
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            while (true) {
                if (val % 2 == 0) {
                    try {
                        p2(lock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "T1");
        Thread t2 = new Thread(() -> {
            while (true) {
                if (val % 2 == 1) {
                    try {
                        p2(lock);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "T2");
        t1.start();
//        Thread.sleep(1000);
        t2.start();
    }


    private static void p(Object lock) throws InterruptedException {
        synchronized (lock) {
            lock.notify();
            System.out.println(Thread.currentThread().getName());
            lock.wait();
        }
    }

    static volatile int val = 0;

    private static void p2(Object lock) throws InterruptedException {
        synchronized (lock) {
            Thread.sleep(1000);
            val++;
            System.out.println(Thread.currentThread().getName()+":"+val);
        }
    }
}

