package com.xianyue.basictype.lamabda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import org.apache.commons.collections4.CollectionUtils;

/**
 * collect 是一个非常有用的终端操作，它可以将流中的元素转变成另外一个不同的对象，例如一个List，Set或Map。collect
 * 接受入参为Collector（收集器），它由四个不同的操作组成：供应器（supplier）、累加器（accumulator）、组合器（combiner）和终止器（finisher）
 *
 * <p>组合器一般在并行运行时会使用到
 */
public class ReduceSample {

    public static void main(String[] args) {
        baseReduce();

        mergeReduce();

        // 自定义累加器 Collector
        Collector<Item, StringJoiner, String> personNameCollector =
            Collector.of(
                () -> new StringJoiner(" | "),          // supplier 供应器
                (j, p) -> j.add(p.getName().toUpperCase()),  // accumulator 累加器
                (j1, j2) -> j1.merge(j2),               // combiner 组合器
                StringJoiner::toString);                // finisher 终止器
    }

    private static void mergeReduce() {
        List<TestReduce> list1 = new ArrayList<>();
        list1.add(new TestReduce(Arrays.asList("hello")));
        list1.add(new TestReduce(Arrays.asList("world")));
        list1.add(new TestReduce(Arrays.asList("!")));

        for (int i = 0; i < 10000; i++) {
            TestReduce result1 = list1.stream().reduce(new TestReduce(new ArrayList<>()), TestReduce::merge);
            String res = result1.effes.toString();
            if (!res.equalsIgnoreCase("[hello, world, !]")) {
                System.out.println(res);
                break;
            }
        }
    }

    private static void baseReduce() {
        List<String> list = Arrays.asList("hello", " world", "!");
        String result = list.stream().reduce("hi, ", (t, u) -> t + u);
        System.out.println(result);
    }


    public static class TestReduce {

        private List<String> effes;

        public TestReduce(List<String> effes) {
            this.effes = effes;
        }

        public TestReduce merge(TestReduce reduce) {
            if (CollectionUtils.isNotEmpty(reduce.effes)) {
                this.effes.addAll(reduce.effes);
            }
            return this;
        }
    }


}
