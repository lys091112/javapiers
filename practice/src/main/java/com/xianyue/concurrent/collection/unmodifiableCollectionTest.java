package com.xianyue.concurrent.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Xianyue
 * unmodifiableCollection不能直接修改，但可以修改originalCollection
 */
public class unmodifiableCollectionTest {
    public static void main(String[] args) {
        Collection<User> originalCollection = new ArrayList<>();
        originalCollection.add(new User("a"));
        originalCollection.add(new User("b"));
        originalCollection.add(new User("c"));
        Collection<User> unmodifiableCollection = Collections.unmodifiableCollection(originalCollection);
        for (User user : originalCollection) {
            user.name = "ssss";
        }

        System.out.println("Original Size=" + unmodifiableCollection.size());
        originalCollection.add(new User("d"));
        System.out.println("New Size=" + unmodifiableCollection.size());
        for (User user : unmodifiableCollection) {
            System.out.println(user.name);
        }
    }
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }
}
