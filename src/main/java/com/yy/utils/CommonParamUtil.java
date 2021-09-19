package com.yy.utils;

import com.yy.async.CommonParamTransform;
import org.springframework.util.Assert;

import java.util.Objects;

public class CommonParamUtil {

    /* 本地线程使用 */
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /* 线程间传输使用 */
    private static final TransformNamedThreadLocal<CommonParamTransform> transformContextHolder
            = new TransformNamedThreadLocal<>("transform context");

    /* 获取 主线程 threadlocal */
    public static CommonParamTransform getPublicContext() {
        return new CommonParamTransform().setIp(holder.get());
    }

    /* 赋值 目标线程 threadlocal */
    public static void setTransformContext(CommonParamTransform context) {
        if (Objects.isNull(context)) {
            transformContextHolder.remove();
        }else {
            transformContextHolder.set(context);
        }
    }

    /* 获取 子线程 threadlocal */
    public static CommonParamTransform getTransformContext() {
        return transformContextHolder.get();
    }

    /* 清除 子线程 threadlocal */
    public static void cleanTransformContext() {
        if (Objects.nonNull(getTransformContext())) {
            transformContextHolder.remove();
        }
    }


    /*
     * @Description: 静态内部ThreadLocal类 传输用
     *
     * @return: ThreadLocal
     * @Author: yloopdaed
     * @Date: 9:05 下午 2021/8/19
     */
    public static class TransformNamedThreadLocal<T> extends ThreadLocal<T> {

        private final String name;

        public TransformNamedThreadLocal(String name) {
            Assert.hasText(name, "Name must not be empty");
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

    }
}
