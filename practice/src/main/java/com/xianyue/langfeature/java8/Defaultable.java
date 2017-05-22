package com.xianyue.langfeature.java8;

/**
 */
public interface Defaultable {

    default void notRequired() {
        System.out.println("default impletemention");
    }

}
