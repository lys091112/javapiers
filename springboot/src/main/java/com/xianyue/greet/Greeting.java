package com.xianyue.greet;

/**
 *@author  XianYue
 */
public class Greeting {

    private final int id;
    private final String context;

    public Greeting(int id, String context) {
        this.id = id;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public int getId() {
        return id;
    }
}
