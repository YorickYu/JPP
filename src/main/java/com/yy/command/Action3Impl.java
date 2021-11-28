package com.yy.command;

/**
 * @className: Action3Impl
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class Action3Impl implements Action {
    private final Executor executor;

    public Action3Impl(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void perform() {
        executor.func3();
        System.out.println("Action3Impl");
    }
}
