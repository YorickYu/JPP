package com.yy.dcl;

/**
 * 单例的一种写法
 * 解决线程同步问题，可以防止反序列化
 */
public enum SingletonEnum {
    INSTANCE;
    public void m() {
        System.out.println("m func ");
    }
}
