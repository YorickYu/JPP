package com.yy.concurrentHashMap;

import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock rl = new ReentrantLock();

        new Thread(()->{
            rl.lock();
            System.out.println("thread 1 执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 1 结束");
            rl.unlock();
        }).start();

        Thread.sleep(100);

        new Thread(()->{

            while (!rl.tryLock()) {
                System.out.println("找锁");
                rl.lock();
                break;
            }
            System.out.println("thread 1 执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 1 结束");
            rl.unlock();
        }).start();
    }
}
