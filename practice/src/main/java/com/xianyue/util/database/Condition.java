package com.xianyue.util.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xianyue
 *
 * TODO 是否可以进行多个builder，分别进行不同部分的build。
 */
public class Condition {
    private String value;
    private String connector;  //and or
    private List<Condition> conditions = new ArrayList<>();

    public Condition(String value, String connector) {
        this.value = value;
        this.connector = connector;
    }

    public Condition() {}

    void and(Condition condition) {
        conditions.add(condition);
    }

    void or(Condition condition) {
        conditions.add(condition);
    }

    void and(String value) {
        and(new Condition(value, "and"));
    }

    void or(String value) {
        or(new Condition(value, "or"));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    public String getConnector() {
        return connector;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (null != connector) {
            sb.append(" ").append(connector).append(" ");
        }
        sb.append(value);
        if (!conditions.isEmpty()) {
            boolean oneMore = conditions.size() > 1;
            if (oneMore && null != connector) {
                sb.append("(");
            }
            for (Condition c : conditions) {
                sb.append(c.toString());
            }
            if (oneMore && null != connector) {
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
