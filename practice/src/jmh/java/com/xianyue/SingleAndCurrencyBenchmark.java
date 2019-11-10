package com.xianyue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 自然数求和的串行和并行算法性能测试
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class SingleAndCurrencyBenchmark {

    @Param({"10000", "100000", "1000000"})
    private int length;

    private int[] numbers;
    private Calculator singleThreadCalc;
    private Calculator multiThreadCalc;

//    public static void main(String[] args) throws Exception {
//        Options opt = new OptionsBuilder()
//            .include(SingleAndCurrencyBenchmark.class.getSimpleName())
//            .forks(1)
//            .warmupIterations(5)
//            .measurementIterations(2)
//            .build();
//        Collection<RunResult> results = new Runner(opt).run();
//    }

    @Benchmark
    public long singleThreadBench() {
        return singleThreadCalc.sum(numbers);
    }

    @Benchmark
    @Threads(value = 4)
    public long multiThreadBench() {
        return multiThreadCalc.sum(numbers);
    }

    @Setup
    public void prepare() {
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleThreadCalc = new SinglethreadCalculator();
        multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
    }

    @TearDown
    public void shutdown() {
        singleThreadCalc.shutdown();
        multiThreadCalc.shutdown();
    }


    static class SinglethreadCalculator implements Calculator {

        @Override
        public long sum(int[] numbers) {
            LongAdder sum = new LongAdder();
            for (int i : numbers) {
                sum.add(i);
            }
            return sum.sum();
        }

        // LongAddr 的使用有性能消耗
//        public long sum(int[] numbers) {
//            long sum = 0;
//            for (int i : numbers) {
//                sum += i;
//            }
//            return sum;
//        }

        @Override
        public void shutdown() {
            // do nothing
        }
    }

    static class MultithreadCalculator implements Calculator {

        int avavailableProcessors;

        public MultithreadCalculator(int availableProcessors) {
            this.avavailableProcessors = availableProcessors;
        }

        @Override
        public long sum(int[] numbers) {
            LongAdder sum = new LongAdder();
            CountDownLatch downLatch = new CountDownLatch(avavailableProcessors);
            int cycle = (numbers.length + avavailableProcessors - 1) / avavailableProcessors;
            for (int i = 0; i < avavailableProcessors; i++) {
                int temp = i;
                new Thread(() -> {
                    for (int j = temp * cycle; (j < (temp + 1) * cycle) && j < numbers.length; j++) {
                        sum.add(numbers[j]);
                    }

                    downLatch.countDown();
                }).start();
            }
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                // do nothing
            }
            return sum.sum();
        }

        List<List<Integer>> split(int[] numbers) {
            List res = new ArrayList();
            int cycle = (numbers.length + avavailableProcessors - 1) / avavailableProcessors;
            for (int i = 0; i < avavailableProcessors; i++) {
                List<Integer> temp = new ArrayList<>(cycle);
                for (int j = 0; j < cycle; j++) {
                    temp.add(numbers[i * cycle + j]);
                }
                res.add(temp);
            }
            return res;
        }

        @Override
        public void shutdown() {

        }
    }
}