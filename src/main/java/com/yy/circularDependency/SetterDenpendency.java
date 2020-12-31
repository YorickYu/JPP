package com.yy.circularDependency;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.SpringVersion;

public class SetterDenpendency {


    public static void main(String[] args) {
        System.out.println(SpringVersion.getVersion());

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CircleA a = context.getBean(CircleA.class);
        CircleB b = context.getBean(CircleB.class);
    }
}
