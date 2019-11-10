package com.xianyue.basictype.concurrent.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ComparatorDemo {

    //编写Comparator,根据User的id对User进行排序
    private static final Comparator<User> COMPARATOR = new Comparator<User>() {
        public int compare(User o1, User o2) {
            return o1.getAge() - o2.getAge();//运用User类的compareTo方法比较两个对象
        }
    };

    public static void main(String[] args) {
        ArrayList<User> student = new ArrayList<User>();
        student.add( new User(1, "yueliming", 12));
        student.add(new User(2, "yueliming", 14));
        student.add(new User(3, "yueliming", 13));

        Collections.sort(student, COMPARATOR);//用我们写好的Comparator对student进行排序
        for (int i = 0; i < student.size(); i++) {
            System.out.println(student.get(i).getId());
        }

        boolean needupdate = false;
        needupdate = (3 != 5);
        System.out.println(needupdate);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class User {

        private int id;
        private String name;
        private int age;
    }
}