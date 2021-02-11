package com.yy.heap;

import java.util.Random;

// -Xms1m -Xmx1m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
public class OOMTest {

    public static void main(String[] args) {
        String s = "abc";

        try {
            while (true) {
                s += new Random().nextDouble();
                s += new Random().nextDouble();
                s += new Random().nextDouble();
            }
        }catch (Error e) {
            e.printStackTrace();
        }
    }
}
