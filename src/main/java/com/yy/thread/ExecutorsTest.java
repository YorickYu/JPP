package com.yy.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.quartz.simpl.SimpleThreadPool;

import java.util.concurrent.ThreadFactory;

public class ExecutorsTest {
    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();


    }
}
