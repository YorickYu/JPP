package com.yy.async;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class AsyncInnerTestService {
    @SneakyThrows
    @Async("customThreadPool")
    public CompletableFuture<Integer> testFutureTask(Integer idx) {
        Thread.sleep(1000);

        return CompletableFuture.completedFuture(idx);
    }
}