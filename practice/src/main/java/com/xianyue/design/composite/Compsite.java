package com.xianyue.design.composite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Compsite extends Component {

    private Map<String, Component> childrens = new HashMap<>();

    public Compsite(String name) {
        super(name);
    }

    @Override
    public void Add(Component component) {
        if (null == component) {
            throw new NullPointerException("component can't be null");
        }
        childrens.put(component.getName(), component);
    }

    @Override
    public void Remove(Component component) {
        if (null == component) {
            throw new NullPointerException("component can't be null");
        }
        childrens.remove(component.getName());
    }

    @Override
    public void execute(ComponentParam param) {
        Iterator<Entry<String, Component>> iterator = childrens.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Component> entry = iterator.next();
//            System.out.println("children name -> " + entry.getKey() + " value: ");
            entry.getValue().execute(param);
        }
    }

}
