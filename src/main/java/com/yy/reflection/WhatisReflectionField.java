package com.yy.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class WhatisReflectionField {

    static class Student extends Person {
        public int score;
        private int grade;

        public Student() {
        }

        public Student(int grade) {
            this.grade = grade;
        }
    }

    static class Person {
        public String name;
    }

    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        Field grade;
        System.out.println(grade = stdClass.getDeclaredField("grade"));

        // ========================================
        String name = grade.getName();
        System.out.println("name = " + name);
        Class<?> type = grade.getType();
        System.out.println("type = " + type);
        int modifiers = grade.getModifiers(); // 返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
        System.out.println("modifiers = " + modifiers);

        Modifier.isFinal(modifiers); // false
        Modifier.isPublic(modifiers); // false
        Modifier.isProtected(modifiers); // false
        Modifier.isPrivate(modifiers); // true
        Modifier.isStatic(modifiers); // false

        // ========================================

        // field.get(o) 获取值
        // IllegalAccessException 因为grade是private
        // 解决: grade.setAccessible(true); 或者 将grade更改为public
        grade.setAccessible(true); // 一律允许访问，可能会失败
        Student s = new Student(3);
        // get
        Object value = (int)grade.get(s);
        System.out.println("value = " + value);
        // set
        grade.set(s, 9);
        System.out.println("value = " + s.grade);



    }
}


