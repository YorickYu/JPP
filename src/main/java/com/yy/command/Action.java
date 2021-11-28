package com.yy.command;

/**
 * @className: Action
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public interface Action {
    default void perform() {
        System.out.println("default");
    }
}
