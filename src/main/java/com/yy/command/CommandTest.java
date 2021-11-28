package com.yy.command;

/**
 * @className: CommandTest
 * @description:
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class CommandTest {
    public static void main(String[] args) {
        Marco marco = new Marco();
        ExecutorImpl executor = new ExecutorImpl();
        marco.record(new Action1Impl(executor));
        marco.record(new Action2Impl(executor));
        marco.record(new Action3Impl(executor));
        marco.go();
    }
}
