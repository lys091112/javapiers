package com.xianyue.springboot.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author Xianyue
 * 控制台输出顺序： initializing bean --> postConstruct bean --> after propertiesSet
 */
public class InnitialzingBeanTest implements InitializingBean, DisposableBean{

    public InnitialzingBeanTest() {
        System.out.println("----------------------->");
        System.out.println("initializing bean");
        System.out.println("----------------------->");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after propertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("postConstruct init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destory bean");
    }
}
