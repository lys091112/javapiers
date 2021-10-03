package com.xianyue.basictype.reflect;

public class SupGenericSub<T> extends Sup<T> {

    public T getObject() {
        System.out.println("--------------");
        return super.getObject();
    }
}