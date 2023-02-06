package com.yy.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @className: SortUtil
 * @description:
 * @author: yloopdaed
 * @date: 2022/1/6
 **/
public class SortUtil {
    public static void main(String[] args) {
        String[] strings = new String[]{"string1", "string2", "string3"};
        final int[] ints = new int[]{40, 32, 34};

        final List<String> stringListCopy = Arrays.asList(strings);
        ArrayList<String> sortedList = new ArrayList(stringListCopy);
        Collections.sort(sortedList, Comparator.comparing(s -> ints[stringListCopy.indexOf(s)]));


        List<String> recall = Arrays.asList("4", "3", "9", "1", "7", "5");
        List<String> list = Arrays.asList("1", "3", "4", "9", "0");

        list.sort(Comparator.comparing(recall::indexOf));
        System.out.println(list);
    }
}
