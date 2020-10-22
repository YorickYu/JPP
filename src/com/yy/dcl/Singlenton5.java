package com.yy.dcl;

public class Singlenton5 {
    private static volatile Singlenton5 INSTANCE; // JIT

    private Singlenton5() {}
    // 5 DCL
    public static Singlenton5 getInstance() {
        if (INSTANCE == null) { // double check 1
            synchronized (Singlenton5.class) { // LOCK
                if (INSTANCE == null) { // double check 1
                    INSTANCE = new Singlenton5();
                }
            }
        }
        return INSTANCE;
    }
}
