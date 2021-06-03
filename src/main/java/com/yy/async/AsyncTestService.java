package com.yy.async;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncTestService {

    @Resource
    private AsyncInnerTestService asyncInnerTestService;

    @SneakyThrows
    @Async("pool")
    public CompletableFuture<Integer> testFutureTask(Integer idx) {
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(idx);
    }

    @Async("pool")
    public CompletableFuture<Integer> testForLoopFutureTask(Integer idx) {
        CompletableFuture<Integer> arr[] = new CompletableFuture[5];
        for (int i = 0; i < 5; i++) {
            CompletableFuture<Integer> future = asyncInnerTestService.testFutureTask(i);
            arr[i] = future;
        }
        CompletableFuture.allOf(arr).join();
        return CompletableFuture.completedFuture(idx);
    }
}
