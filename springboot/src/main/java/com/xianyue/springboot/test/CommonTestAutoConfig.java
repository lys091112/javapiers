package com.xianyue.springboot.test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configurable what's this

@Configuration
public class CommonTestAutoConfig {

  @Bean
  public AwareBean awareBean() {
    return new AwareBean();
  }

  @Bean
  public MyAwareService myAwareService() {
    return new MyAwareService();
  }

  @Bean
  public InitializingBean createInnitializingBean() {
    return new InnitialzingBeanTest();
  }
}
