package com.xianyue.basictype.lamabda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class ReduceSample {

    public static void main(String[] args) {
        baseReduce();

        mergeReduce();
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
