package com.xianyue.design.composite;

public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void Add(Component component) {
        throw new UnsupportedOperationException("unsupport operation!");
    }

    @Override
    public void Remove(Component component) {
        throw new UnsupportedOperationException("unsupport operation!");
    }

    @Override
    public void execute(ComponentParam param) {
        System.out.println("i am the leaf, name is " + getName());
    }
}
