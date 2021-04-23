package com.yy.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

    static class ParallelStreams {
        public static long sequentialSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .reduce(0L, Long::sum);
        }

        public static long parallelSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .parallel() // 在内部实际上就是设了一个boolean标志，调用后开始并行执行
                    .reduce(0L, Long::sum);
        }
        public static long iterativeSum(long n) {
            long result = 0;
            for (long i = 1L; i <= n; i++) {
                result += i;
            }
            return result;
        }
        public static long parallelRangedSum(long n) {
            return LongStream.rangeClosed(1, n)
                    .parallel()
                    .reduce(0L, Long::sum);
        }
    }

    public static void main(String[] args) {

        // 93 msecs
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");

        // 4 msecs
        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");

        // 101 msecs
        // iterate生成的是装箱的对象，必须拆箱成数字才能求和；
        // 我们很难把iterate分成多个独立块来并行执行。
        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs" );

        // 0 msecs
        System.out.println("Parallel range sum done in:" +
                measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");
    }



    // 普通累加
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
