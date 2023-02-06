package com.yy.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;
    public ForkJoinSumTask(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumTask leftTask =
                new ForkJoinSumTask(numbers, start, start + length/2);

        ForkJoinSumTask rightTask =
                new ForkJoinSumTask(numbers, start + length/2, end);
        leftTask.fork();
        rightTask.fork();
        Long rightResult = rightTask.join();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 100).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumTask(numbers);
        Long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }

}
