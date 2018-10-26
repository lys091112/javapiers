package com.xianyue.basictype.concurrent.collection;

import java.util.Comparator;

/**
 * @author Xianyue
 */
public class OwnComparator {

    //lamabda简写形式
    private Comparator<Long> compator = (Long o1, Long o2) -> (int) (o1 - o2);

    public static void main(String[] args) {

    }
}
