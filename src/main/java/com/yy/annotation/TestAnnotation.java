package com.yy.annotation;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestAnnotation {


    @Log.START(description = "asdfzxxzxzzxz")
    public void hah777(String string, Integer integer) {
        System.out.println("hah777");
    }
    @Log.END(description = "测试End注解")
    public Map resulttest777(String string, Integer integer) {
        Map<String, Integer> m = new HashMap<>();
        m.put(string, integer);
        m.put("2", integer);
        return m;
    }

    @Log.MIDDLE(description = "标记测试方法")
    public void aroundTest(String s) {
        // TODO..
    }

    @MyAnnotation(descrption = "test field using")
    private String name;

    @MyAnnotation(descrption = "test method using")
    public void run() {
        System.out.println("TestAnnotation::run");
    }

    public static void main(String[] args) {
        Class<TestAnnotation> testAnnotationClass = TestAnnotation.class;
        // 获取方法上的注解
        for (Method m: testAnnotationClass.getDeclaredMethods()) {
            MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
            if (annotation == null)
                continue;
            System.out.println(m.getName()); // 获取方法名称
            System.out.println(annotation.descrption());
        }

        // 获取属性上的注解
        for (Field f: testAnnotationClass.getDeclaredFields()) {
            MyAnnotation annotation = f.getAnnotation(MyAnnotation.class);
            if (annotation == null)
                continue;
            System.out.println(f.getName()); // 获取属性名称
            System.out.println(annotation.descrption());
        }
    }





}
