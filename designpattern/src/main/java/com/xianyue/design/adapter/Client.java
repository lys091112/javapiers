package com.xianyue.design.adapter;

/**
 * Created by langle on 2018/1/21.
 */
public class Client {

    public static void main(String[] args) {
        Adaptee adaptee = new ConcreteAdaptee();

        Adapter adapter = new Adapter();
        adapter.setAdaptee(adaptee);

        adapter.twoPoint();

    }

}
