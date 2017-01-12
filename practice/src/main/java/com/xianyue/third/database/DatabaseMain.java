package com.xianyue.third.database;


/**
 * @author Xianyue
 */
public class DatabaseMain {

    public static void main(String[] args) {
        String k = "4.2.2";
        int inx1 = k.indexOf(".");
        int inx2 = k.indexOf(".", 2);
        System.out.println(k.substring(0, inx1));
        System.out.println(k.substring(inx1 + 1, inx2));

        String sql = SelectSqlBuilder.sql().column("MaxTime", "").from("student").where("name = 3").and("value = 'aa'").and("age > 15").toString();
        System.out.println(sql);

    }
}
