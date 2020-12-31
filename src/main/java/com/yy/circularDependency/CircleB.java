package com.yy.circularDependency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircleB {
    @Autowired
    private CircleA a;

    public CircleB() {
        System.out.println("init CircleB!");
    }
}
