package com.yy.leecode;

import java.util.ArrayList;
import java.util.Comparator;

public class RunningTest {

    public static void print() {
        System.out.println("hahahahaha");
    }
    public void printn() {
        System.out.println("xxxxxxxxxx");
    }


    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");

        arrayList.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });

        new Thread(RunningTest::print).start();
        new Thread(new RunningTest()::printn).start();

//        Comparator.comparing()
    }

}
