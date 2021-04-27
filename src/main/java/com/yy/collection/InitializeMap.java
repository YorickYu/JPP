package com.yy.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitializeMap {
    
    /** 
     * @Description 静态代码块初始化
     *        
     * @Author yloopdaed
     * @Date 11:42 2021/4/27
     */
    public static Map<String, String> articleMapOne;
    static {
        articleMapOne = new HashMap<>();
        articleMapOne.put("ar01", "Intro to Map");
        articleMapOne.put("ar02", "Some article");
    }

    /**
     * @Description {{}}
     *
     * NOTE:
     * we must try to avoid this initialization technique because it creates an anonymous extra class at every usage,
     * holds hidden references to the enclosing object, and might cause memory leak issues.
     *
     * @Author yloopdaed
     * @Date 11:48 2021/4/27
     */
    Map<String, String> doubleBraceMap  = new HashMap<String, String>() {{
        put("key1", "value1");
        put("key2", "value2");
    }};

    /**
     * @Description Collections中singletonMap静态方法
     *
     * 不可修改的单例Map
     *
     * @Author yloopdaed
     * @Date 11:44 2021/4/27
     */
    public static Map<String, String> createSingletonMap() {
        return Collections.singletonMap("username1", "password1");
    }

    /**
     * @Description Java8 toMap()
     *
     * @Author yloopdaed
     * @Date 13:45 2021/4/27
     */
    public static Map<String, String> createMapJava8() {
        Map<String, String> map = Stream.of(new String[][] {
                { "Hello", "World" },
                { "你好", "世界" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        return map;
    }

    public static Map<String, String> createImmuableMapJava8() {
        Map<String, String> map = Stream.of(new String[][] {
                { "Hello", "World" },
                { "你好", "世界" },
        }).collect(Collectors.collectingAndThen(Collectors.toMap(data -> data[0], data -> data[1]), Collections::unmodifiableMap));
        return map;
    }

}
