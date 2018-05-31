package com.xianyue.beauty;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * java 8 模拟 range
 */
public class Range implements Iterable<Long> {

    Stream<Long> range;

    public Range(final Long start, final Long end) {
        this(start, end, 1L);
        if (start > end) {
            throw new IllegalArgumentException("start must be lower than end!");
        }
    }

    public Range(final Long start, final Long length, final Long step) {
        Supplier supplier = new Supplier() {
            private long pos = start;

            @Override
            public Object get() {
                long next = pos;
                pos = pos + step;
                return next;
            }
        };

        range = Stream.generate(supplier).limit(length);
    }

    @Override
    public Iterator<Long> iterator() {
        return range.iterator();
    }


    public static void main(String[] args) {
        new Range(1L, 10L, 3L).forEach(t -> System.out.println(t));
        new Range(0L, 10L).forEach(t -> System.out.println(t));
    }
}
