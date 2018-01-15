package com.xianyue.design.command;

public class Client {

    /**
     * 命令模式： 用于“行为请求者”与“行为实现者”解耦
     * @param args
     */
    public static void main(String[] args) {
        Target receiver = new Door();

        Invoker invoker = new Invoker(new OpenDoorCommand(receiver));

        invoker.invoke();
    }

}
