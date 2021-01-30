package com.yy.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamParallelDemo {
    public static void main(String[] args) {
        System.out.println(String.format("本计算机的核数：%d", Runtime.getRuntime().availableProcessors()));

        // 产生100w个随机数(1 ~ 100)，组成列表
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000_0000);

        for (int i = 0; i < 1000_0000; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = getCurrentTime();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("单线程计算耗时：%d", getCurrentTime() - prevTime));


        prevTime = getCurrentTime();
        // parallelStream() 效率相同
        list.stream().parallel().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("多线程计算耗时：%d", getCurrentTime() - prevTime));
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 本计算机的核数：8
     * 494958293
     * 单线程计算耗时：179
     * 494958293
     * 多线程计算耗时：39
     */
}
