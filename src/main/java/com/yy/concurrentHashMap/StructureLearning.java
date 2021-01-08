package com.yy.concurrentHashMap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class StructureLearning {
    private static sun.misc.Unsafe UNSAFE = null;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.putIfAbsent("test", "abc");

        Class sc = String[].class;
        int ss = UNSAFE.arrayIndexScale(sc); // 4
        int sbase = UNSAFE.arrayBaseOffset(sc); //16
        int sshift = 31 - Integer.numberOfLeadingZeros(ss); // 31 - 29 = 2
        System.out.println(Integer.numberOfLeadingZeros(4)); // 32-3 = 29

        int index = (4 << sshift) + sbase;
        System.out.println(index);
    }
}
