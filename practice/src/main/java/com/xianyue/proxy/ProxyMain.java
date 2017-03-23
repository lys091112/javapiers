package com.xianyue.proxy;

import com.xianyue.proxy.cglib.BookFacadeImple2;
import com.xianyue.proxy.cglib.CglibFacadeProxy;
import com.xianyue.proxy.own.BookFacade;
import com.xianyue.proxy.own.BookFacade1;
import com.xianyue.proxy.own.BookFacadeProxy;

public class ProxyMain {

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade book = (BookFacade)proxy.bind(new BookFacade1());
        book.addBook();
        
        CglibFacadeProxy cglicProxy = new CglibFacadeProxy();
        BookFacadeImple2 book2 = (BookFacadeImple2)cglicProxy.getInstance(new BookFacadeImple2());
        int k = (int)book2.addBook();
        System.out.println(k);
    }
}
