package com.xianyue.classloader;

import com.xianyue.packageScan.EnumFileType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author  Xianyue
 * java 默认的classLoader。
 *   * BootstrapClassLoader  --> 加载路径固定，bin/java ... jre/lib/... jre/classes/...
 *   * ExtClassLoader    --> JAVA_HOME/jre/lib/ext....
 *   * AppClassLoader    --> java运行的本地路径
 *
 */
public class SingleClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);  //不使用父类加载的class
        byte[] classData = getClazzData(name);
        if(classData == null) {
            throw new ClassNotFoundException();
        }

        Class clazz = defineClass(name, classData,0,classData.length);
        return clazz;

    }

    private byte[] getClazzData(String name) {
        if(null == name || "".equals(name)){
            return null;
        }
        String path = convertNameToPath(name);
        InputStream is = null;
        try {
            URL url = new URL(path);
            byte[] buf = new byte[1024];
            int len = -1;
            is = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len = is.read(buf)) != -1) {
                baos.write(buf,0,len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    private String convertNameToPath(String name) {

        return "/home/crescent/workspace/javapiers/filescanner/build/classes/" + name.replaceAll("\\.","/") + ".class";

    }

    public static void main(String[] args) {
        java.net.URL[] systemUrls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (java.net.URL url: systemUrls){
            System.out.println(url.toExternalForm());
        }


        ClassLoader loader = com.xianyue.classloader.SingleClassLoader.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);

        Class fileType = null;
        try {
            SingleClassLoader singleClassLoader = new SingleClassLoader();
            fileType = singleClassLoader.loadClass("com.blueocn.tps.v2.metrics.service.MetricService");

            System.out.println(fileType.getClassLoader());

            SingleClassLoader singleClassLoader2 = new SingleClassLoader();
            Class fileType2 = singleClassLoader2.loadClass("com.blueocn.tps.v2.metrics.service.MetricService");
//            System.out.println(fileType2.getClassLoader());
        } catch (ClassNotFoundException e) {
            System.out.println("class not found!");
        }

    }
}
