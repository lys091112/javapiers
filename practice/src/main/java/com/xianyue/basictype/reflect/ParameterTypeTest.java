package com.xianyue.basictype.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuhongjun
 * @since 上午10:12 18-7-17
 */
public class ParameterTypeTest extends Sup<String> {

    private List<String> list;
    private Map<Long, Integer> map;
    private List list2;

    public static void main(String[] args) throws Exception {
        Field listField = ParameterTypeTest.class.getDeclaredField("list");
        System.out.println(listField.getType());
        System.out.println(listField.getGenericType());

        for (Type type : ((ParameterizedType) listField.getGenericType()).getActualTypeArguments()) {
            System.out.println(type);
        }

        System.out.println();
        System.out.println("-----------list2");
        Field listField2 = ParameterTypeTest.class.getDeclaredField("list2");
        // 判断是否有泛型
        if (((Class) listField.getGenericType()).isAssignableFrom(ParameterizedType.class)) {
            for (Type type : ((ParameterizedType) listField2.getGenericType()).getActualTypeArguments()) {
                System.out.println(type);
            }
        }

        System.out.println();
        System.out.println("------------- map -------------------------");
        Field mapField = ParameterTypeTest.class.getDeclaredField("map");
        System.out.println(mapField.getType());
        System.out.println(mapField.getGenericType());

        for (Type type : ((ParameterizedType) mapField.getGenericType()).getActualTypeArguments()) {
            System.out.println(type);
        }

        System.out.println();
        System.out.println("---------- class ---------------");
        ParameterTypeTest test = new ParameterTypeTest();
        System.out.println(test.getClass().getGenericSuperclass());
        ParameterizedType type = ((ParameterizedType) test.getClass().getGenericSuperclass());
        System.out.println("rowType " + type.getRawType());
        System.out.println("ownType " + type.getOwnerType());
        for (Type t : type.getActualTypeArguments()) {
            System.out.println(t);
        }
    }

}
