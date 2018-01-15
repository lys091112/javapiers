package com.xianyue.design.command;

public class Invoker {

    private ICommand command;

    public Invoker(ICommand command) {
        this.command = command;
    }

    public void invoke() {
        this.command.execute();
    }


}
