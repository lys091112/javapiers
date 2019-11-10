package com.xianyue.design.composite;

public class Client {

    public static void main(String[] args) {
        Compsite root = new Compsite("root");

        Compsite rightKey = new Compsite("rightKey");
        rightKey.Add(new Leaf("right--001"));
        rightKey.Add(new Leaf("right--002"));

        Compsite leftKey = new Compsite("leftKey");
        leftKey.Add(new Leaf("left--001"));
        leftKey.Add(new Leaf("left--002"));

        root.Add(leftKey);
        root.Add(rightKey);

        root.Add(new Leaf("special"));


        root.execute(new ComponentParam());

    }

}
