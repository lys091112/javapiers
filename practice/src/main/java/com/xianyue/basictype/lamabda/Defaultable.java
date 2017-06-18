package com.xianyue.basictype.lamabda;

/**
 */
public interface Defaultable {

    default void notRequired() {
        System.out.println("default impletemention");
    }

}
