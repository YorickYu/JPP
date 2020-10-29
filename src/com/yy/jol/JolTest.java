package com.yy.jol;

import org.openjdk.jol.info.ClassLayout;

class FieldTest{
    byte a;
    int c;
    boolean d;
    long e;
    Object f;
}

public class JolTest {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        FieldTest d = new FieldTest();
        System.out.println(ClassLayout.parseInstance(d).toPrintable());


//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }

    }
}
