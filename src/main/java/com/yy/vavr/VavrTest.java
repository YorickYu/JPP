package com.yy.vavr;

import cn.hutool.poi.excel.cell.CellEditor;
import com.yy.lambda.Functional;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @className: VavrTest
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/24
 **/
public class VavrTest {

    public static void main(String[] args) {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String string = java8._1();
        Integer integer = java8._2();


        // (vavr, 1)
        Tuple2<String, Integer> that1 = java8.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );

        // (vavr, 1)
        Tuple2<String, Integer> that2 = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );

        // "vavr 1"
        String that3 = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );



        System.out.println("123");

    }

    private void groupAndCollect() {
        List<Integer> collect = Stream.of(4, 1, 2, 3, 1).map(item -> item * 2).collect(Collectors.toList());
        Map<Boolean, List<Integer>> map = collect.stream().collect(Collectors.groupingBy(item -> item < 5));
        List<Integer> collect1 = map.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

}
