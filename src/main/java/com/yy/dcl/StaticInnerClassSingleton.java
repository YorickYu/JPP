package com.yy.dcl;

/**
 * 静态内部类方式
 * JVM保证单例、懒加载
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {}
    private static class Holder {
        private final static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    public static StaticInnerClassSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
