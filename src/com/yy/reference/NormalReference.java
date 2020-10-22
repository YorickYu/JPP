package com.yy.reference;

import java.io.IOException;

class M {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("M finalize");
    }
}

public class NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.out.println(m);
        System.in.read();
    }
}
