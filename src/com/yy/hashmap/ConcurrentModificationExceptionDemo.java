package com.yy.hashmap;


import java.util.HashMap;
import java.util.Iterator;


public class ConcurrentModificationExceptionDemo {

    static void threadRemove() {
        HashMap m = new HashMap();
        for (int i = 0; i <100 ; i++) {
            m.put(String.valueOf(i), "value"+i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator iterator = m.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = (String) iterator.next();
                    if (Integer.parseInt(next) % 2 == 0) {
                        System.out.println("thead 1");
                        iterator.remove();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator iterator  = m.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = (String) iterator.next();
                    System.out.println(m.get(next));
                }
            }
        }).start();

    }

    static void atomicTest() throws InterruptedException {

        HashMap m = new HashMap();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(i);
                    m.put(i, String.valueOf(i).hashCode());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10000; i < 20000; i++) {
                    System.out.println(i);
                    m.put(i, String.valueOf(i).hashCode());
                }
            }
        }).start();

        Thread.sleep(5000);
        Iterator iterator = m.keySet().iterator();
        iterator.next(); // 对比modCount

    }


    public static void main(String[] args) throws InterruptedException {
//        ConcurrentModificationExceptionDemo.threadRemove();
        ConcurrentModificationExceptionDemo.atomicTest();


    }
}
