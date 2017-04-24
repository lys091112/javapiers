package com.xianyue.springboot.config;

import com.xianyue.springboot.test.InnitialzingBeanTest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xianyue
 */
@Configuration
public class CommonConfig {


    @Bean
    public InitializingBean createInnitializingBean() {
        return new InnitialzingBeanTest();
    }
}
