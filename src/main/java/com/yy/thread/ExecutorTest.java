package com.yy.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


public class ExecutorTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExecutorTest.class);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 5; i++) {
            pool.execute(()->{
                System.out.println("abc");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            LOGGER.info("线程还在执行。。。");
        }
        long end = System.currentTimeMillis();
        LOGGER.info("一共处理了[{}]", (end - start)+"ms");


    }

}
