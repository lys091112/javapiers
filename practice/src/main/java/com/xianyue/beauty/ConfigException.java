package com.xianyue.beauty;


/**
 * 这仅仅是一个demo，所以代码没有任何含义，仅仅是为了保证依赖类不报错
 */
public class ConfigException extends Throwable {

    public ConfigException(String name, Object value, String s) {
        super(name);
    }

    public ConfigException(String name, Exception e) {
        super(name);
    }

    public ConfigException(String name) {
        super(name);
    }
}
