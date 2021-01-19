package com.yy.thread;

import com.yy.thread.pool.ThreadPoolTools;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolTools threadPoolTools = new ThreadPoolTools();
        long start = System.currentTimeMillis();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 50; i++) {
            threadPoolTools.execute(() -> {
                System.out.println("abcd");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicInteger.addAndGet(1);
            });
        }
        threadPoolTools.executor.shutdown();
        while (!threadPoolTools.executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行。。。");
        }
        long end = System.currentTimeMillis();
        System.out.println("一共处理了:"+ (end - start)+"ms");
        if (atomicInteger.get() == 20) {
            System.out.println("nice");
        }


    }
}
