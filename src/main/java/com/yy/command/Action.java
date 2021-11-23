package com.yy.command;

/**
 * @className: Action
 * @description: TODO 类描述
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public interface Action {
    public default void perform() {
        System.out.println("default");
    }
}
