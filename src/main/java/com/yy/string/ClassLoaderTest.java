package com.yy.string;


import java.util.ArrayList;

public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoaderTest s = new ClassLoaderTest();
        System.out.println("s.getClass() = " + s.getClass()); // class com.yy.string.ClassLoaderTest
        System.out.println("s.getClass().getClassLoader() = " + s.getClass().getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("s.getClass().getClassLoader().getParent() = " + s.getClass().getClassLoader().getParent()); // sun.misc.Launcher$ExtClassLoader@5ca881b5
        System.out.println("s.getClass().getClassLoader().getParent().getParent() = " + s.getClass().getClassLoader().getParent().getParent()); // null 在c++中，获取不到

//        String string = new String();
//        System.out.println("string.getClass().getClassLoader() = " + string.getClass().getClassLoader());

    }
}

//class String {
//    private int a;
//}
