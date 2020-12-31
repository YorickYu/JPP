package com.yy.circularDependency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircleA {
    @Autowired
    private CircleB b;

    public CircleA() {
        System.out.println("init CircleA!");
    }
}
