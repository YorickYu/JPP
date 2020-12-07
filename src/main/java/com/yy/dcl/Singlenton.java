package com.yy.dcl;

public class Singlenton {

    private static final Singlenton INSTANCE = new Singlenton();

    private Singlenton() {}

    public static Singlenton getInstance() {
        return INSTANCE;
    }
}
