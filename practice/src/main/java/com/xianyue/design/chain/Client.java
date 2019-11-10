package com.xianyue.design.chain;

public class Client {

    public static void main(String[] args) {
        //
        Handler handler = new ConcreteHandler();
        Handler handler2 = new Concrete2Handler();

        handler.setNext(handler2);

        handler.handle(new HandleParam(30));

        handler.handle(new HandleParam(31));

        handler.handle(new HandleParam(-1));

    }
}
