package com.github.jvmlearn.utils;

/**
 * 类的工具类
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c = null;
        try {
            c = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            // do nothing
        }

        if (null == c) {
            // no thread classloader
            c = ClassUtils.class.getClassLoader();
            if (null == c) {
                // no class loader
                try {
                    c = ClassLoader.getSystemClassLoader();
                } catch (Exception e) {
                    // can't access system classloader
                }
            }
        }

        return c;

    }

}
