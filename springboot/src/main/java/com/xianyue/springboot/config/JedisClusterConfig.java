package com.xianyue.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xianyue
 * 该类使用了Java注解，@Configuration与@Bean，
 * 在方法上使用@Bean注解可以让方法的返回值为单例，
 * 该方法的返回值可以直接注入到其他类中去使用
 * @Bean注解是方法级别的
 *
 * 如果使用的是常用的spring注解@Component，
 * 在方法上没有注解的话，方法的返回值就会是一个多例，
 * 该方法的返回值不可以直接注入到其他类去使用
 * 该方式的注解是类级别的
 */
@Configuration
public class JedisClusterConfig {
    @Autowired
    private RedisPropertiesConfig redisPropertiesConfig;

    @Bean
    public JedisCluster getJedisCluster() {
        if(!redisPropertiesConfig.isEnabled()) {
            return null;
        }

        String[] addresses = redisPropertiesConfig.getClusterNodes().split(";");
        if(addresses.length == 0) {
            throw  new RuntimeException("redis cluster must be not null");
        }
        Set<HostAndPort> nodes = new HashSet<>();
        for (String addr: addresses ) {
            nodes.add(HostAndPort.parseString(addr));
        }
        return new JedisCluster(nodes, redisPropertiesConfig.getCommandTimeout());
    }
}