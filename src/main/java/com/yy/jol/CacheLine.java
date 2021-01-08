package com.yy.jol;

import java.util.concurrent.CountDownLatch;

public class CacheLine {

    public final static class FilledLong {
        /**value 加 p1 - p6；加对象头8个字节正好等于一缓存行的大小 */
        //markWord + klass (32位机,64位是16字节) 8字节
        public volatile long value = 0L; // 8字节
        public long p1, p2, p3, p4, p5, p6; //48字节
    }

    public static long COUNT = 1_0000_0000L;
    private static class T {
        public long p1, p2, p3, p4, p5, p6, p7;
        public volatile long x;
        public long p9, p10, p11, p12, p13, p14, p15;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);

    }
}
