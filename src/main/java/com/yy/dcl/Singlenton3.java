package com.yy.dcl;

public class Singlenton3 {

    private static Singlenton3 INSTANCE;

    private Singlenton3() {}

    // 3 getInstance方法上锁
    public static synchronized Singlenton3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singlenton3();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i< 100; i++) {
            new Thread(() -> {
                System.out.println(Singlenton3.getInstance().hashCode());
            }).start();
        }
    }

}
