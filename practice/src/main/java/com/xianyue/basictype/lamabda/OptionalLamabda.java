package com.xianyue.basictype.lamabda;

import java.util.Optional;

public class OptionalLamabda {

    public static void main(String[] args) {
        Optional<Long> o1 = Optional.of(1L);
        System.out.println(o1.map(t -> String.valueOf(t)).map(t -> "hell0" + t).get());

        Optional<Long> o2 = Optional.empty();
        System.out.println(o2.map(t -> String.valueOf(t)).map(t -> "hell0" + t).get());
    }
}
