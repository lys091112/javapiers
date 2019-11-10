package com.xianyue.design.decorator;

public class ConcreteDecoratortTwo extends Decorator {

    public ConcreteDecoratortTwo(Component component) {
        super(component);
    }

    @Override
    public void execute() {
        System.out.println("concrete decorator two before");
        super.execute();
        System.out.println("concrete decorator two after");
    }
}
