package com.yy.annotation;

import java.lang.annotation.*;

enum LogType
{
    START, END, MIDDLE;
}

public class Log {

    @Target(ElementType.ANNOTATION_TYPE)
    @interface l {
        String description() default "";
    }

    /**
     * 方法开始执行前记录
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @l
    @interface START {
        String description() default "没有留下描述";
    }

    /**
     * 方法执行结束后记录
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface END {
        String description() default "没有留下描述";
    }

    /**
     * 方法执行过程中记录
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface MIDDLE {
        String description() default "没有留下描述";
    }

}
