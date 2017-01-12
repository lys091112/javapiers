package com.xianyue.collection;

import java.util.Comparator;

/**
 * @author Xianyue
 */
public class Common {

    private Comparator<Long> compator = new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            long k = 6L;
            return (int)k;
        }
    };

    public static void main(String[] args) {

    }
}
