package com.yy.outoforder;

public class Singlenton {

    private static final Singlenton INSTANCE = new Singlenton();

    private Singlenton() {}

    public Singlenton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Object o = new Object();
    }
}
