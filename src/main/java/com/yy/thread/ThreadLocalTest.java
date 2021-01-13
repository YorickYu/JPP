package com.yy.thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ThreadLocalTest {

    static class Hee {

        int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    private final static String s = "aa";

    public static void main(String[] args) {
        ThreadLocal t1 = new ThreadLocal();
        t1.set("t1");
        ThreadLocal t2 = new ThreadLocal();

        new ThreadLocal<>().set("be GC");
        System.gc();


//        new Thread(() -> {
//            ThreadLocal t3 = new ThreadLocal();
//            t1.set("t3");
//            ThreadLocal t4 = new ThreadLocal();
//            t2.set("t4");
//        }).start();

//        ThreadLocalTest.Hee s = null;
//        ThreadLocal.ThreadLocalMap map = null;

        TreeSet set = new TreeSet();
        set.add("abc");        // unchecked warning


        set.remove("abc");
//        System.gc();
//        System.out.println("t1.get() = " + t1.get());
        System.out.println("t2.get() = " + t2.get());

    }
}
