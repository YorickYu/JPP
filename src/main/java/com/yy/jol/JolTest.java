package com.yy.jol;

import org.openjdk.jol.info.ClassLayout;

class OrderTest{
    byte a;
    int c;
    boolean d;
    long e;
    People f;
}

class People {
    int age;
    String name;
}

public class JolTest {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        OrderTest d = new OrderTest();
        System.out.println(ClassLayout.parseInstance(d).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
