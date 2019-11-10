package com.xianyue.design.proxy;

import com.xianyue.design.proxy.cglib.BookFacadeImple2;
import com.xianyue.design.proxy.cglib.CglibFacadeProxy;
import com.xianyue.design.proxy.own.BookFacade;
import com.xianyue.design.proxy.own.BookFacade1;
import com.xianyue.design.proxy.own.BookFacadeProxy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ProxyMain {

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade book = (BookFacade) proxy.bind(new BookFacade1());
//        for (Field field : book.getClass().getDeclaredFields()) {
//            System.out.println(field.getName());
//            System.out.println(field.getType());
//        }
//        for(Method method: book.getClass().getDeclaredMethods()) {
//            System.out.println(method.getName());
//        }
        book.addBook();
        CglibFacadeProxy cglicProxy = new CglibFacadeProxy();
        BookFacadeImple2 book2 = (BookFacadeImple2) cglicProxy.getInstance(new BookFacadeImple2());
        int k = (int) book2.addBook();
        System.out.println(k);
    }
}
