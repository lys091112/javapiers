package com.xianyue.basictype.reflect;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author liuhongjun
 * @since 上午10:12 18-7-17
 */
public class ParameterTypeSubT extends SupSub {

    public static void main(String[] args) throws Exception {
        ParameterTypeSubT p = new ParameterTypeSubT();

        if (p.getClass().getGenericSuperclass() instanceof ParameterizedTypeImpl) {
            System.out.println("is generic");
        }

        if (p.getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedTypeImpl) {
            System.out.println("super is generic");
        }

    }

}
