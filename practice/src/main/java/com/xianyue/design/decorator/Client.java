package com.xianyue.design.decorator;

public class Client {

    public static void main(String[] args) {
        //
        Component component = new ConcreteComponent();
        Component component1 = new ConcreteDecoratortTwo(new ConcreteDecoratorOne(component));
        component1.execute();
    }

}
