package com.yy.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class VectorTest {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        Iterator<Integer> iterator1 = vector.iterator();
        iterator1.forEachRemaining( item -> {
            System.out.println(item);
        });

        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> iterator2 = array.iterator();
        iterator2.forEachRemaining( item -> System.out.println(item));
    }
}
