package com.xianyue.packageScan;

import java.io.File;

/**
 * @author  XianYue
 */
public class PathUtil {

    /**
     *  注意.的正则表达
     */
    public static String packageToPath(String packageName) {
       if(null == packageName || "".equals(packageName))  {
           throw new IllegalArgumentException("packageName cann't be null");
       }
       return packageName.replaceAll("\\.", File.separator);
    }


    /**
     * 将fileName 转化为包路径
     */
    public static String pathToPackage(String pathName) {
        if(null == pathName || "".equals(pathName)) {
            throw new IllegalArgumentException("fileName cann't be null");
        }
        if(pathName.startsWith("/")) {
            pathName = pathName.substring(1);
        }
        return pathName.replaceAll("/", ".");
    }

    /**
     *  将多个字符串串联起来
     */
    public static String contactStr(String ... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str: strs ) {
           sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 去除文件的后缀名
     */
    public static String trimSuffix(String fileName) {
        int index = fileName.indexOf(".");
        if(index == -1) {
            return fileName;
        }
        return fileName.substring(0,index);
    }


}
