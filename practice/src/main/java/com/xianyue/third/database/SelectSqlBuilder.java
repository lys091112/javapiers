package com.xianyue.third.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xianyue
 */
public class SelectSqlBuilder {
//    private String operator;    //select,insert,update,delete
    private StringBuilder sql = new StringBuilder();
    private List<SelectItem> items = new ArrayList<>();
    private Condition condition = new Condition();
    private String    table;

    private SelectSqlBuilder() {
    }

    public static SelectSqlBuilder sql() {
        return new SelectSqlBuilder();
    }

    //创建sql语句的结果集
    public SelectSqlBuilder column(String columnName, String aliasColunmnName) {
        items.add(new SelectItem(columnName, aliasColunmnName));
        return this;
    }


    public SelectSqlBuilder where(String condition) {
        this.condition.setValue(condition);
        return this;
    }

    public SelectSqlBuilder and(String condition) {
        this.condition.and(condition);
        return this;
    }
    public SelectSqlBuilder or(String condition) {
        this.condition.or(condition);
        return this;
    }

    //对sql条件进行联合
    public SelectSqlBuilder combine(String condition1,String condition2) {

        return this;
    }

    public SelectSqlBuilder from(String table) {
        this.table = table;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("select");
        for (SelectItem item: items ) {
           sb.append(item);
        }
        sb.append(" from ").append(table).append(" where ");
        sb.append(condition);
        return sb.toString();
    }
}
