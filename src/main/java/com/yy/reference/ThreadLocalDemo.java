package com.yy.reference;


import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    private static class Person {
        /*volatile*/ String name = "yy";
        /*volatile*/ long age = 18;
    }

    static ThreadLocal<Person> t = new ThreadLocal<>();

    public static void main(String[] args) {
        Person p = new Person();
        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
            System.out.println(p.age);
            System.out.println(t.get());
            t.remove();
        }).start();

        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "zz";
            p.age = 20;
            t.set(new Person());
        }).start();

    }
}
