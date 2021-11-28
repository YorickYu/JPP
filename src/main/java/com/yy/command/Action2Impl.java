package com.yy.command;

/**
 * @className: Action2Impl
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class Action2Impl implements Action {
    private final Executor executor;

    public Action2Impl(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void perform() {
        executor.func2();
        System.out.println("Action2Impl");
    }
}
