package com.yy.collection;


import cn.hutool.core.lang.Assert;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

    @Data
    @Accessors(chain = true)
    public static class Animal {
        private int id;
        private String name;
    }

    // Java8之前
    public static Map<Integer, Animal> convertListBeforeJava8(List<Animal> list) {
        Map<Integer, Animal> map = new HashMap<>();
        for (Animal animal : list) {
            map.put(animal.getId(), animal);
        }
        return map;
    }

    // Java8之后
    public static Map<Integer, Animal> convertListAfterJava8(List<Animal> list) {
        return list.stream().collect(Collectors.toMap(Animal::getId, animal -> animal));
    }

    // Java8之后 处理key冲突 - IllegalStateException
    public static Map<Integer, Animal> convertListAfterJava8_conflict(List<Animal> list) {
        return list.stream().collect(Collectors.toMap(Animal::getId, animal -> animal, (ov, nv) -> nv));
    }

    // Guava Maps
    public static Map<Integer, Animal> convertListWithGuava(List<Animal> list) {
        return Maps.uniqueIndex(list, Animal::getId);
    }

    public static void main(String[] args) {
        Animal pig = new Animal().setName("pig").setId(1);
        Animal dog = new Animal().setName("dog").setId(2);
        Animal cat = new Animal().setName("cat").setId(3);
        List<Animal> list = Arrays.asList(pig, dog, cat);

        Map<Integer, Animal> map1 = convertListBeforeJava8(list);
        Map<Integer, Animal> map2 = convertListAfterJava8(list);
        Map<Integer, Animal> map3 = convertListWithGuava(list);
        Map<Integer, Animal> map4 = convertListAfterJava8_conflict(list);

    }
}
