package com.yy.lambda;

/**
 *  @FunctionalInterface 注解
 *  只允许有一个 abstract 方法声明
 *  多个 default 或 static 方法声明
 */
@FunctionalInterface
public interface Functional<T> {
    int handle(T t1, T t2);
    default void detail() {

    }
    static void cons() {

    }
}
