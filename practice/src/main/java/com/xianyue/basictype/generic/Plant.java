package com.xianyue.basictype.generic;

public class Plant<AA extends Fruit> {

    public AA fruit;

    public void setFruit(AA fruit) {
        this.fruit = fruit;
    }

    public AA getFruit() {
        return this.fruit;
    }

}
