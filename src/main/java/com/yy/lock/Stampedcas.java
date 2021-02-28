package com.yy.lock;

import java.util.concurrent.locks.StampedLock;

public class Stampedcas {

    private double x, y;
    private final StampedLock sl = new StampedLock();

    // 写锁的使用
    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp); // 释放写锁
        }
    }

    // 乐观读锁的使用
    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead(); // 获取乐观读锁
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) { // //检查乐观读锁后是否有其他写锁发生，有则返回false
            stamp = sl.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 悲观读锁以及读锁升级写锁的使用
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock(); // 悲观读锁
        try {
            while (x == 0.0 && y == 0.0) {
                // 读锁尝试转换为写锁：转换成功后相当于获取了写锁，转换失败相当于有写锁被占用
                long ws = sl.tryConvertToWriteLock(stamp);

                if (ws != 0L) { // 如果转换成功
                    stamp = ws; // 读锁的票据更新为写锁的
                    x = newX;
                    y = newY;
                    break;
                }
                else { // 如果转换失败
                    sl.unlockRead(stamp); // 释放读锁
                    stamp = sl.writeLock(); // 强制获取写锁
                }
            }
        } finally {
            sl.unlock(stamp); // 释放所有锁
        }
    }
}
