package com.yy.async;

import com.yy.utils.CommonParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class PoolConfig {
    @Bean("pool")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "taskDecoratorPool")
    public ThreadPoolTaskExecutor taskDecoratorPoolExecutor() {
        return getThreadDecoratorPoolTaskExecutor(10, 10, 180, 1000, "taskDecoratorPool-");
    }

    private ThreadPoolTaskExecutor getThreadDecoratorPoolTaskExecutor(int corePoolSize, int maxPoolSize, int keepAliveSeconds, int queueCapacity, String threadNamePrefix) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setKeepAliveSeconds(keepAliveSeconds);
        pool.setQueueCapacity(queueCapacity);
        pool.setThreadNamePrefix(threadNamePrefix);
        pool.setTaskDecorator(new TransformTaskDecorator());
        pool.initialize();
        return pool;
    }

    @Slf4j
    static class TransformTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            CommonParamTransform context = CommonParamUtil.getPublicContext();
            log.info("装饰器生效前，主线程context：{}", context);
            return () -> {
                try {
                    CommonParamUtil.setTransformContext(context);
                    log.info("装饰器生效中，进入子线程：{}", Thread.currentThread().getName());
                    runnable.run();
                } finally {
                    CommonParamUtil.cleanTransformContext();
                    log.info("装饰器生效后，清空子线程context");
                }
            };
        };
    }
}


