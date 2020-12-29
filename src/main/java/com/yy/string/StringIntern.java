package com.yy.string;

public class StringIntern {

    public static void main(String[] args) {
        String s = "javazs";
        System.out.println(s.toString() == s.intern());

        StringBuilder builder = new StringBuilder("j").append("avazs");
        String s1 = builder.toString();
        System.out.println(s1 == s1.intern());

    }
}
