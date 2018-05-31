package com.xianyue.design.decorator;


public class ConcreteComponent implements Component {

    @Override
    public void execute() {
        System.out.println("---> this is a concrete Component");
    }
}
