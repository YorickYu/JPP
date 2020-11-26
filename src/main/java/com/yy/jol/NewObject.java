package com.yy.jol;

public class NewObject {
    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            new Object();
//        }
//        System.out.println("use time: " + (System.currentTimeMillis() - startTime) + "ms");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            Object o = new Object();
            o.hashCode();
        }
        System.out.println("use time: " + (System.currentTimeMillis() - startTime) + "ms");

    }
}
