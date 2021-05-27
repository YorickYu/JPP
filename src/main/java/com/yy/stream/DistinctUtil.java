package com.yy.stream;


import com.yy.stream.model.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DistinctUtil {

    // predicate to filter the duplicates by the given key extractor.
    public static <T> Predicate<T> distinctByKey1(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> uniqueMap = new ConcurrentHashMap<>();
        return t -> uniqueMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    // use Set interface
    public static <T> Predicate<T> distinctByKey2(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static void main(String[] args) {
        Animal pig = new Animal().setName("pig").setId(1);
        Animal dog1 = new Animal().setName("dog").setId(2);
        Animal dog2 = new Animal().setName("dog").setId(4);
        Animal cat1 = new Animal().setName("cat1").setId(3);
        Animal cat2 = new Animal().setName("cat2").setId(3);
        List<Animal> list = Arrays.asList(pig, dog1, dog2, cat1, cat2);

        List<Animal> res = list.stream().filter(distinctByKey1(Animal::getId)).filter(distinctByKey2(Animal::getName)).collect(Collectors.toList());
        System.out.println(res);
    }
}
