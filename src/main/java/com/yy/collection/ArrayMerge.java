package com.yy.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayMerge {

    @Data
    @Accessors(chain = true)
    static
    class Item {
        private String name;
        private String desc;
    }

    public static void main(String[] args) {
        Item item1 = new Item().setDesc("1").setName("zhangsan");
        Item item2 = new Item().setDesc("2").setName("lisi");
        Item item3 = new Item().setDesc("3").setName("wangwu");
        Item item4 = new Item().setDesc("4").setName("zhaoliu");
        List<Item> list1 = new ArrayList<>();
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        list1.add(item4);

        Item item5 = new Item().setDesc("1").setName("tian");
        Item item6 = new Item().setDesc("2").setName("ming");
        Item item7 = new Item().setDesc("3").setName("ning");
        Item item8 = new Item().setDesc("4").setName("");
        List<Item> list2 = new ArrayList<>();
        list2.add(item5);
        list2.add(item6);
        list2.add(item7);
        list2.add(item8);
        List<Item> mergeList = merge1(list1, list2);
        list1.add(item6);
        list2.add(new Item());
        System.out.println(mergeList.size());
    }

    /**
     * @Description before 8
     *
     * @Author yloopdaed
     * @Date 21:01 2021/4/26
     */
    // Generic method to join two lists in Java
    public static<T> List<T> merge1(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>();

        list.addAll(list1);
        list.addAll(list2);

        return list;
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge2(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>(list1);
        list.addAll(list2);

        return list;
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge3(List<T> list1, List<T> list2)
    {
        return new ArrayList<T>() {{
            addAll(list1);
            addAll(list2);
        }};
    }

    // Method to join two lists in Java
    public static List<String> merge4(List<String> list1, List<String> list2)
    {
        List<String> list = new ArrayList<>();

        Collections.addAll(list, list1.toArray(new String[0]));
        Collections.addAll(list, list2.toArray(new String[0]));

        return list;
    }

    /**
     * @Description Java 8
     *
     * @Author yloopdaed
     * @Date 21:02 2021/4/26
     */
    // Generic method to join two lists in Java
    public static<T> List<T> merge5(List<T> list1, List<T> list2)
    {
        return Stream.of(list1, list2)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge6(List<T> list1, List<T> list2)
    {
        List<T> list = new ArrayList<>();
        Stream.of(list1, list2).forEach(list::addAll);

        return list;
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge7(List<T> list1, List<T> list2)
    {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge8(List<T> list1, List<T> list2)
    {
        List<T> list = list1.stream().collect(Collectors.toList());
        list.addAll(list2);

        return list;
    }

    /**
     * @Description Guava
     *
     * @Author yloopdaed
     * @Date 21:05 2021/4/26
     */
    // Generic method to join two lists in Java
    public static<T> List<T> merge9(List<T> list1, List<T> list2) {
        return Lists.newArrayList(Iterables.concat(list1, list2));
    }

    // Generic method to join two lists in Java
    public static<T> List<T> merge10(List<T> list1, List<T> list2)
    {
        List<T> list = Lists.newArrayList();

        Iterables.addAll(list, list1);
        Iterables.addAll(list, list2);

        return list;
    }
}
