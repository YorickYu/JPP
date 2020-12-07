package com.yy.dcl;

import java.util.concurrent.atomic.AtomicInteger;

public class PlusPlus {
    private long i = 0;
    private long addl() {
        return i++;
    }

    public static void main(String[] args) {
        PlusPlus p = new PlusPlus();
        p.addl();

//        AtomicInteger;
    }
}
