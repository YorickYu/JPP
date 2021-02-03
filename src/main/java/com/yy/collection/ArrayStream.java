package com.yy.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayStream {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println("squaresList = " + squaresList);
        // [9, 4, 4, 9, 49, 9, 25]
        // [9, 4, 49, 25]
        numbers.stream().reduce((a,b) -> {
            System.out.println("=======a+b :"+a+b);
            return a+b;
        });

        Integer[] list = new Integer[]{1,2,3,4,5};
        Stream<Integer> stream = Arrays.stream(list);
        List<Integer> integers = stream.collect(Collectors.toList());
        System.out.println("stream = " + integers);

//        integers.parallelStream().forEachOrdered(); 有序（同步）
        integers.parallelStream().forEach(item -> { // 无序（异步）
            System.out.println(item);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("filtered = " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        Random random = new Random();
        random.ints().limit(3).forEach(System.out::println);
        random.ints().limit(3).sorted().forEach(System.out::println);


    }
}
