package com.yy.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubListTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> sublist = list.subList(0, 2);
        list.add("sb"); // list.add() 中调用了  modCount++，而subList中的modCount没有被同步，所以会发生fast-fail，ConcurrentModificationException
        // 正确使用
        // 原list不能修改，下面方法可以将sublist独立封装，修改sublist不会再影响原list
        sublist = Lists.newArrayList(sublist); // guava
        List<String> collect = list.stream().skip(0).limit(2).collect(Collectors.toList());
        collect.add("aasdf");
        System.out.println(collect);
    }
}
