package com.yy.command;

/**
 * @className: Action1Impl
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class Action1Impl implements Action {

    private final Executor executor;

    public Action1Impl(Executor executor) {
        this.executor = executor;
    }


    @Override
    public void perform() {
        executor.func1();
        System.out.println("Action1Impl");
    }

}
