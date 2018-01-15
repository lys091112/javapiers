package com.xianyue.design.decorator;

public abstract class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void execute() {
        component.execute();
    }
}
