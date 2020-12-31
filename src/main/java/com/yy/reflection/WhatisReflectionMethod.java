package com.yy.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class WhatisReflectionMethod {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("setScore", int.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        Method grade;
        System.out.println(grade = stdClass.getDeclaredMethod("setGrade", int.class));


        // ======================================
        String methodName = grade.getName();
        Class<?> returnType = grade.getReturnType();
        int parameterCount = grade.getParameterCount();
        Class<?>[] parameterTypes = grade.getParameterTypes();
        int modifiers = grade.getModifiers();
        Modifier.isFinal(modifiers);
        Modifier.isPublic(modifiers);
        Modifier.isProtected(modifiers);
        Modifier.isPrivate(modifiers);
        Modifier.isStatic(modifiers);

        // ====================================

        // 普通方法、私有方法
        Student student = new Student();
        grade.setAccessible(true);
        Object invoke = grade.invoke(student, 7);
        System.out.println("student.grade = " + student.grade);

        // 静态方法
        int i = Integer.parseInt("1");
        Method parseInt = Integer.class.getMethod("parseInt", String.class);
        Object res = parseInt.invoke(null, "20");
        System.out.println("res = " + res);

        // 多态
        Method personHelloMethod = Person.class.getMethod("hello");
        personHelloMethod.invoke(new Student());


        // =====================================
        // 调用无参数构造方法
        Person person = Person.class.newInstance();
        System.out.println("person = " + person);

        // 有参数构造方法
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);
    }
}

class Student extends Person {
    public int score;
    public int grade;

    public Student() {
    }

    public void setScore(int score) {
        this.score = score;
    }
    private void setGrade(int year) {
        this.grade = year;
    }

    public void hello() {
        System.out.println("Student:hello");
    }
}

class Person {
    public String getName() {
        return "Person";
    }

    public void hello() {
        System.out.println("Person:hello");
    }
}