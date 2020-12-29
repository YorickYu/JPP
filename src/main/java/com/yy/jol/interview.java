package com.yy.jol;

/*
    面试题：
    实现一个计数器，提供两个方法：add、size
    需求：两个线程，线程1添加元素，线程2监听元素，当元素个数到5时，线程2提示并中断监听
 */

class Counter {
    public long p1, p2, p3, p4, p5, p6, p7;
    public volatile long count = 1;
    public long p9, p10, p11, p12, p13, p14, p15;

    public void add() {
        count++;
    }

    public int size() {
        return (int) count;
    }

}

public class interview {

    public static void main(String[] args) {

        Integer iz = 1000;
        Integer jz = 1001;
        System.out.println(iz<=jz);


        Counter counter = new Counter();
        String s = "a";
        Object o = s;
        System.out.println(o.getClass());
        System.out.println(o.getClass().getSuperclass());

        System.out.println(s.toString());
        System.out.println(o.toString());


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.add();
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (counter.size() >= 5) {
                    System.out.println(">>>>>");
                    break;
                }
            }
        }).start();


    }
}
