package com.xianyue.design.decorator;

public class ConcreteDecoratorOne extends Decorator {

    public ConcreteDecoratorOne(Component component) {
        super(component);
    }

    @Override
    public void execute() {
        System.out.println("concrete decorator one before");
        super.execute();
        System.out.println("concrete decorator one after");
    }
}
