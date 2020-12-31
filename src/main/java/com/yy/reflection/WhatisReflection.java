package com.yy.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Month;
import java.util.HashMap;

public class WhatisReflection {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);


        // get class
        // class
        Class<String> stringClass = String.class;
        Class<Integer> integerClass = int.class;
        Class<String[]> aClass = String[].class;

        // getClass()
        HashMap<Object, Object> m = new HashMap<>();
        Class<? extends HashMap> mClass = m.getClass();

        // forName
        try {
            Class<?> cClass = Class.forName("java.time.Clock");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String name = stringClass.getName();
        String packageName = stringClass.getPackage().getName();
        Field[] fields = stringClass.getFields(); // getDeclaredFields()
        Method[] methods = stringClass.getMethods(); // getDeclaredMethods()

        // NoSuchFieldException
        Field hashField = stringClass.getDeclaredField("hash"); // getField()
        // NoSuchMethodException
        Method toStringMethod = stringClass.getMethod("toString"); // getDeclaredMethod()
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
        System.out.println();
    }

}
