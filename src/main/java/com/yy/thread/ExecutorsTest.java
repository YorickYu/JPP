package com.yy.thread;

import com.yy.thread.pool.ThreadPoolTools;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolTools threadPoolTools = new ThreadPoolTools();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            threadPoolTools.execute(ExecutorsTest::run);
        }
        threadPoolTools.executor.shutdown();
        while (!threadPoolTools.executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行。。。");
        }
        long end = System.currentTimeMillis();
        System.out.println("一共处理了:"+ (end - start)+"ms");
    }

    private static void run() {
        System.out.println("abcd");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
