package com.xianyue.design.command;

public class Door extends Target {


    @Override
    void open() {
        System.out.println("open the door");
    }

    @Override
    void close() {
        System.out.println("close the door");
    }

    @Override
    void alarm() {
        System.out.println("door alarm");
    }
}
