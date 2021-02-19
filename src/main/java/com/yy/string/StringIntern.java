package com.yy.string;

public class StringIntern {

    public static void main(String[] args) {

//        String s = "javazs";
//        System.out.println(s == s.intern()); // true

        StringBuilder builder = new StringBuilder("j").append("avazs");
        String s1 = builder.toString();
        System.out.println(s1 == s1.intern()); // false，返回的"javazs"是s对象的引用

        String s2 = new String("javazs");
        System.out.println(s2 == s2.intern()); // false，返回的"javazs"是s对象的引用

        String nj = new String("java");
        System.out.println(nj == nj.intern()); // false，系统内常量池中已经添加了字符串java

    }
}
