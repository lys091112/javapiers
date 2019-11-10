package com.xianyue.design.composite;

import lombok.Getter;

public abstract class Component {

    @Getter
    private String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void Add(Component component);

    public abstract void Remove(Component component);

    public abstract void execute(ComponentParam param);


}
