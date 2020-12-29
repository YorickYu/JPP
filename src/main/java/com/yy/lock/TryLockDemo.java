package com.yy.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock rl = new ReentrantLock();

        new Thread(()->{
            rl.lock();
            System.out.println("thread 1 执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 1 结束");
            rl.unlock();
        }).start();

        Thread.sleep(100);

        AtomicInteger a = new AtomicInteger(10);

        new Thread(()->{

            while (!rl.tryLock()) {
                System.out.println("找锁");
                a.addAndGet(10); // do something
                rl.lock(); //  不加异常，java.lang.IllegalMonitorStateException
                break;
            }
            System.out.println("thread 2 执行: a = " + a.get());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 2 结束");
            rl.unlock();
        }).start();
    }
}
