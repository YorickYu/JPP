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
        marco.record(new Action1Impl());
        marco.record(new Action2Impl());
        marco.record(new Action3Impl());
        marco.go();
    }
}
