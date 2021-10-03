package com.xianyue.basictype.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SupSub extends Sup<String> {

    public String getObject() {
        System.out.println("--------------");
        return super.getObject();
    }

    /**
     * 获取T的实际类型
     */
    public void testSup() throws NoSuchFieldException, SecurityException {
        System.out.println(this.getClass().getSuperclass().getName());
        Type t = this.getClass().getGenericSuperclass();
        System.out.println(t);
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                System.out.print(t1 + ",");
            }
            System.out.println();
        }
    }
}