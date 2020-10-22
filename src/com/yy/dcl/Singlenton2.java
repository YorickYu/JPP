package com.yy.dcl;

public class Singlenton2 {

    private static Singlenton2 INSTANCE;

    private Singlenton2() {}

    // 2 使用时创建
    public static Singlenton2 getInstance() {
        if (INSTANCE == null) {
            // 这里多线程调用时，可能会存在创建出多个实例
            INSTANCE = new Singlenton2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i< 100; i++) {
            new Thread(() -> {
                System.out.println(Singlenton2.getInstance().hashCode());
            }).start();
        }
        long end = System.nanoTime();
        System.out.println("程序运行时间：" + (end-start) + "ns");
    }
}
