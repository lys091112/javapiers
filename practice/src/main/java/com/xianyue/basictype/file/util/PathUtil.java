package com.xianyue.basictype.file.util;

import java.io.File;
import lombok.experimental.UtilityClass;

/**
 * @author liuhongjun
 * @since 下午6:11 18-6-27
 */
@UtilityClass
public class PathUtil {

    /**
     * 获取当前栈的调用者
     */
    public Class<?> getCaller() {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        System.out.println("stack length:" + stack.length);
        if (stack.length < 3) {
            return PathUtil.class;
        }
        String className = stack[2].getClassName();
        System.out.println("getCaller class name:" + className);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return PathUtil.class;
    }

    /**
     * 获取当前类的父路径
     */
    public String currentClassPath(Class<?> clazz) {
        if (clazz == null) {
            clazz = getCaller();
        }

        // 获取jar包的路径
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.replaceFirst("file:/", "");
        path = path.replaceAll("!/", "");
        if (path.lastIndexOf(File.separator) >= 0) {
            path = path.substring(0, path.lastIndexOf(File.separator));
        }
        if (path.substring(0, 1).equalsIgnoreCase("/")) {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.indexOf("window") >= 0) {
                path = path.substring(1);
            }
        }

        return path;
    }


}
