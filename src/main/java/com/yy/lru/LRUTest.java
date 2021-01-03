package com.yy.lru;

public class LRUTest {

    public static void func1() {
        LRULinkedHashMap map = new LRULinkedHashMap(3, true);
        map.put(1, "ricky");
        map.put(2, "jackey");
        map.put(3, "joan");
        System.out.println(map.keySet()); // [1, 2, 3]
        map.put(4, "lily");
        map.put(1, "ricky");
        System.out.println(map.keySet()); // [3, 4, 1]
    }

    public static void func2() {
        LRUCustomMap<Object, Object> map = new LRUCustomMap<>(3);
        map.put(1, "ricky");
        map.put(2, "jackey");
        map.put(3, "joan");
        System.out.println(map.lruMap.keySet()); // [1, 2, 3]
        map.put(4, "lily");
        map.put(1, "ricky");
        System.out.println(map.lruMap.keySet()); // [1, 3, 4]
    }

    public static void main(String[] args) {
        func2();

    }
}
