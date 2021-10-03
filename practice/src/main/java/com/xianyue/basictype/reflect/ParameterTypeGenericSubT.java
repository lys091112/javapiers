package com.xianyue.basictype.reflect;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author liuhongjun
 * @since 上午10:12 18-7-17
 *
 * 参考连接： https://segmentfault.com/a/1190000018319217
 */
public class ParameterTypeGenericSubT extends SupGenericSub<String> {

    public static void main(String[] args) throws Exception {
        ParameterTypeGenericSubT p = new ParameterTypeGenericSubT();

        if (p.getClass().getGenericSuperclass() instanceof ParameterizedTypeImpl) {
            System.out.println("is generic");
        }

        if (p.getClass().getSuperclass().getGenericSuperclass() instanceof ParameterizedTypeImpl) {
            System.out.println("super is generic");
        }

    }

}
