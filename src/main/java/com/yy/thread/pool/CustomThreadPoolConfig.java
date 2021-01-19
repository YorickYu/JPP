package com.yy.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;


@Configuration
public class CustomThreadPoolConfig {

    /**
     * 交给Spring管理
     * @return
     */
    @Bean(value = "customThreadPool")
    public ExecutorService buildCustomThreadPool(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("custom-create-thread-%d").build(); // guava

        ExecutorService pool = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

}
