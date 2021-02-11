package com.yy.heap;

public class HeapInfo {

    public static void main(String[] args) {

        /**
         * 默认：
         * 程序启动分配的最大内存 max：电脑物理内存的 1/4
         * 程序启动的初始化内存 total: 电脑物理内存的 1/64
         */
        long free = Runtime.getRuntime().freeMemory();
        System.out.println("free:" + free + " B " + free/1024L/1024 + "MB"); // free:377822800 K 360MB

        long max = Runtime.getRuntime().maxMemory();
        System.out.println("max:" + max + " B " + max/1024L/1024 + "MB");// max:5717360640 K 5452MB

        long total = Runtime.getRuntime().totalMemory();
        System.out.println("total:" + total + " B " + total/1024L/1024 + "MB"); // total:385875968 K 368MB


    }
}
