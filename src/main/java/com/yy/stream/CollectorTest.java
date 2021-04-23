package com.yy.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CollectorTest {

    public static void main(String[] args) {
        List<Integer> numsWithoutNull = Stream.of(1,2,3,4,5,6,7,8,9,10)
                .collect(() -> new ArrayList<Integer>(),(list, item) -> list.add(item),(list1, list2) -> list1.addAll(list2));
        System.out.println(numsWithoutNull);

        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        l.stream().collect(() -> new ArrayList<Integer>(),(list, item) -> list.add(item),(list1, list2) -> list1.addAll(list2));

    }
}
