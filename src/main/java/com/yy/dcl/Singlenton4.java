package com.yy.dcl;

public class Singlenton4 {

    private static Singlenton4 INSTANCE;

    private Singlenton4() {}

    // 3 getInstance方法上锁
    public static Singlenton4 getInstance() {
        if (INSTANCE == null) {
            // 这里多线程调用时，可能会存在创建出多个实例
            synchronized (Singlenton4.class) {
                INSTANCE = new Singlenton4();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                System.out.println(Singlenton4.getInstance().hashCode());
            }).start();
        }
    }

}
