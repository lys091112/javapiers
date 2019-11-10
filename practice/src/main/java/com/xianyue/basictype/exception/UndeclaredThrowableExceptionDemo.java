package com.xianyue.basictype.exception;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuhongjun
 * @since 2019-11-08
 *
 * 1. 真实的SQLException被包装了两层，先被InvocationTargetException包装，再被UndeclaredThrowableException包装。
 * 其中，InvocationTargetException为受检异常，UndeclaredThrowableException为运行时异常
 *
 * 2. 在debug模式下， ide会重复调用代理类的toString方法，造成 代理函数中的处理被多次执行，例如该例的proxy begin 会被多次打印，通过打印 method name ，也会发现该方法是toString 方法的执行
 */
public class UndeclaredThrowableExceptionDemo {

    static AtomicInteger count = new AtomicInteger(0);

    public interface IService {

        public void test();
    }

    public static class IServiceImpl implements IService {

        @Override
        public void test() {
            throw new UnsupportedOperationException("this is a test");
        }
    }

    public static class IServiceProxy implements InvocationHandler {

        private IService obj;

        public IServiceProxy(IService obj) {
            this.obj = obj;
        }

        // 反编译后的代码
        //        public final void test() throws SQLException {
//            try {
//                System.out.println("proxy begin");
//                super.h.invoke(this, m3, (Object[])null);
//            } catch (RuntimeException | SQLException | Error var2) {
//                throw var2;
//            } catch (Throwable var3) {
//                throw new UndeclaredThrowableException(var3);
//            }
//        }
        // 而super.h.invoke(this, m3, (Object[])null) 抛出InvocationTargetException，一次会被Throwable捕获，从而包装
        // 成 UndeclaredThrowableException
        //  解决方法 ： 用抛出InvocationTargetException捕获 super.h.invoke(...)，然后抛出 var3.getCause()

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.println("proxy begin");
            count.incrementAndGet();
//            return method.invoke(obj, args);
            try {
                return method.invoke(obj, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    public static void main(String[] args) {
        //
        IService impl = new IServiceImpl();
        IService proxy = (IService) Proxy.newProxyInstance(impl.getClass().getClassLoader(),
            impl.getClass().getInterfaces(), new IServiceProxy(impl));
        try {
            proxy.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count.intValue());
    }
}
