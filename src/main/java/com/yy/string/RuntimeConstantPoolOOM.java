package com.yy.string;

import java.util.HashSet;

// -XX:PermSize=6M -XX:MaxPermSize=6M
// -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=10M
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        long i = 0;
        while (true) {
            System.out.println(i);
            set.add(String.valueOf(i++ * 1024).intern());
        }
    }
}
