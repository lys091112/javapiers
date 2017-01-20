package com.xianyue.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibFacadeProxy implements MethodInterceptor {
    private Object target;
    
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy proxy) throws Throwable {
        System.out.println("before2");
        Object res = proxy.invokeSuper(obj, arg2);
        System.out.println("after2");
        return res;
    }

}
