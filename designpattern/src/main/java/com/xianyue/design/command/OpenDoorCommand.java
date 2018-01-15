package com.xianyue.design.command;

public class OpenDoorCommand implements ICommand {

    private Target target;

    public OpenDoorCommand(Target target) {
        this.target = target;
    }

    @Override
    public void execute() {
        target.open();
    }
}
