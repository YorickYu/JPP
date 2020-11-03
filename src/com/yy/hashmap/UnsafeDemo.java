package com.yy.hashmap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    private static sun.misc.Unsafe U;
    private static long COUNT_OFFSET;

    static {
        // U = sun.misc.Unsafe.getUnsafe();

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            U = (Unsafe) f.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        int count = 0;

        COUNT_OFFSET = U.objectFieldOffset(UnsafeDemo.class.getDeclaredField("count"));
        U.compareAndSwapInt(UnsafeDemo.class, COUNT_OFFSET, count, count+1);

    }

}
