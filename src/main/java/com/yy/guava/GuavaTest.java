package com.yy.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 测试模块
 01、前世今生
 02、引入 Guava
 03、基本工具
 04、集合
 05、字符串处理
 06、缓存
 */
public class GuavaTest {

    public static void main(String[] args) {
//        testOptional();
//        immutableList();
//        stringControl();
        try {
            testCache();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void testOptional() {
        Optional optional = Optional.empty();
        optional.ifPresent( value -> {
            System.out.println(value);
        });

        boolean present = optional.isPresent();
        if (present)
            System.out.println("optional.get() = " + optional.get());
    }

    public static void immutableList() {
        List list = new ArrayList();
        list.add("雷军");
        list.add("乔布斯");

        List unmodifiableList = Collections.unmodifiableList(list);
//        unmodifiableList.add("马云"); // UnsupportedOperationException
        list.add("马云");


        List<String> stringArrayList = Lists.newArrayList("雷军","乔布斯");
        ImmutableList<String> immutableList = ImmutableList.copyOf(stringArrayList);
        try {
            immutableList.add("马云");
        }catch (UnsupportedOperationException e){
            e.printStackTrace();
        }
    }

    public static void stringControl() {
        // 将string分解成list
        Iterable<String> split = Splitter.on(';')
                .trimResults()
                .omitEmptyStrings()
                .split("170;1238 ; ;   2218");
        split.forEach(item -> {
            System.out.println(item);
        });

        // 将list连接成string
        String join = Joiner.on(";").skipNulls().join(split);
        System.out.println("join = " + join);
        // StringBuilder stringBuilder = joiner.appendTo(new StringBuilder(), "a", "b");
        // System.out.println("stringBuilder.toString() = " + stringBuilder.toString());
    }

    public static void testCache() throws ExecutionException, InterruptedException {

        CacheLoader cacheLoader = new CacheLoader<String, Animal>() {
            // 如果找不到元素，会调用这里
            @Override
            public Animal load(String s) {
                System.out.println("没有找到 = " + s);

                return new Animal("hot", 1);
            }
        };
        LoadingCache<String, Animal> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000) // 容量
                .expireAfterWrite(3, TimeUnit.SECONDS) // 过期时间
                .removalListener(new MyRemovalListener()) // 失效监听器
                .build(cacheLoader); //
        loadingCache.put("狗", new Animal("旺财", 1));
        loadingCache.put("猫", new Animal("汤姆", 3));
        loadingCache.put("狼", new Animal("灰太狼", 4));

        loadingCache.invalidate("猫"); // 手动失效

        loadingCache.get("mao");

        Animal animal = loadingCache.get("狼");
        System.out.println(animal);
        Thread.sleep(4 * 1000);
        // 狼已经自动过去，获取为 null 值报错
        System.out.println(loadingCache.get("狼"));


        for (int i = 0; i < 20; i++) {
            System.out.println(i);
        }
    }


}

/**
 * 缓存移除监听器
 */
class MyRemovalListener implements RemovalListener<String, Animal> {

    @Override
    public void onRemoval(RemovalNotification<String, Animal> notification) {
        String reason = String.format("key=%s,value=%s,reason=%s", notification.getKey(), notification.getValue(), notification.getCause());
        System.out.println("reason:" + reason);
    }
}

class Animal {
    private String name;
    private Integer age;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
