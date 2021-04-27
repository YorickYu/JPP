package com.yy.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description Map合并
 *
 * 参考：https://www.baeldung.com/java-merge-maps
 *
 * @Author yloopdaed
 * @Date 11:41 2021/4/27
 */
public class MapMerge {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Employee {

        private Long id;
        private String name;

        // constructor, getters, setters
    }

    public static void main(String[] args) {
        HashMap<String, Employee> map1 = new HashMap<>();
        HashMap<String, Employee> map2 = new HashMap<>();

        Employee employee1 = new Employee(1L, "Henry");
        map1.put(employee1.getName(), employee1);
        Employee employee2 = new Employee(22L, "Annie");
        map1.put(employee2.getName(), employee2);
        Employee employee3 = new Employee(8L, "John");
        map1.put(employee3.getName(), employee3);

        Employee employee4 = new Employee(2L, "George");
        map2.put(employee4.getName(), employee4);
        Employee employee5 = new Employee(3L, "Henry");
        map2.put(employee5.getName(), employee5);


        /**
         * @Description merge
         *
         * 该方法接收三个参数，一个 key 值，一个 value，一个 BiFunction<? super V, ? super V, ? extends V> remappingFunction
         * 如果给定的 key 不存在，它就变成了 put(key, value) 。
         * 如果 key 已经存在一些值，那么 remappingFunction 可以选择合并的方式，然后将合并得到的 newValue 赋值给原先的 key。
         *
         * @Author yloopdaed
         * @Date 11:04 2021/4/27
         */
        Map<String, Employee> map3 = new HashMap<>(map1);
        /*
         for (Map.Entry<String, Employee> entry : map2.entrySet()) {
            String key = entry.getKey();
            Employee value = entry.getValue();
            map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(), v2.getName()));
         }
         */
        map2.forEach((key, value) -> map3.merge(key, value, (v1, v2) -> new Employee(v1.getId(),v2.getName())));
        System.out.println(map3);


        /**
         * @Description Stream.concat()
         *
         * toMap() 接收三个参数，一个 key 值，一个 value，一个 BinaryOperator<U> mergeFunction
         * 如果 key 不存在，它就变成了 put(key, value) - 这种情况下可以 通过(v1, v2) -> v1, TreeMap::new 进行默认排序
         * 如果 key 已经存在，那么 mergeFunction 可以处理
         *
         * @Author yloopdaed
         * @Date 11:04 2021/4/27
         */
        Map<String, Employee> result = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1, value2) -> new Employee(value2.getId(), value1.getName())));


        /**
         * @Description Stream.of()
         *
         * 与上面方法相似，也是toMap()
         * 只是生成 Stream 流的方式不同
         *
         * @Author yloopdaed
         * @Date 11:38 2021/4/27
         */
        Map<String, Employee> ofResult = Stream.of(map1, map2)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> new Employee(v1.getId(), v2.getName())));
    }

}
