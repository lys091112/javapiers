package com.xianyue.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Xianyue
 */
public class CountTask extends RecursiveTask<Integer> {
    private int number;

    public CountTask(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        for (int i = 0; i < 100000; i++) {
            sum += i;
        }
        return number;
    }
}
