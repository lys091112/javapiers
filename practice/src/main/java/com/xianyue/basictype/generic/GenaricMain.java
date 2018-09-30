package com.xianyue.basictype.generic;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

public class GenaricMain {

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Orange());

        Apple apple = (Apple) fruits.get(0);
        Orange orange = (Orange) fruits.get(1);
        System.out.println(apple.fruitName());
        System.out.println(orange.fruitName());

        // 下界通配符 不影响往里存储，但取的元素只能放到Object里，需要进行强转
        List<? super Fruit> fruits1 = new ArrayList<>();
        fruits1.add(new Apple());
        fruits1.add(new Orange());

        Apple apple1 = (Apple) fruits1.get(0);
        Orange orange1 = (Orange) fruits1.get(1);
        System.out.println(apple1.fruitName());
        System.out.println(orange1.fruitName());

        // 上界通配符，无法添加元素
        List<? extends Apple> fruits2 = new ArrayList<>();
//        fruits2.add(new Apple());
//        fruits2.add(new Orange());

        Plant<Apple> plant = new Plant<>();
        plant.setFruit(new Apple());

        Plant<Fruit> plant1 = new Plant<>();
        plant1.setFruit(new Apple());

        SuperPlant<Apple> plant2 = new SuperPlant<>();
    }

    public List<Fruit> createList() {
        List<Fruit> list = new ArrayList();
        list.add(new Apple());
        list.add(new Orange());
        return list;
    }
}
