package com.xianyue.basictype.jvm.classloader;

import com.xianyue.basictype.jvm.classinnitial.ConstValue;
import java.lang.reflect.Method;
import java.net.URL;
import sun.misc.Launcher;

/**
 * java 默认的classLoader。
 * * BootstrapClassLoader  --> 加载路径固定，bin/java ... jre/lib/... jre/classes/...
 * * ExtClassLoader    --> JAVA_HOME/jre/lib/ext....   配置参数''java.ext.dirs''
 * * AppClassLoader    --> java运行的本地路径
 *
 * 既然是自定义的类加载器， 那么自定义的类加载器加载的类对象是不能在当前环境中显示声明的，只能通过反射来进行操作
 */
public class ClassLoaderPractice {

  public static void main(String[] args) throws Exception {

    URL[] urls = Launcher.getBootstrapClassPath().getURLs();
    for (URL url : urls) {
      System.out.println(url.getPath());
    }

    ConstValue constValue1 = new ConstValue();

    MyURLClassLoader myURLClassLoader = new MyURLClassLoader();
    myURLClassLoader.setDir(System.getProperty("user.dir") + "/jvmlearn/src/main/resources/tempclass");
    try {
      Class<?> constValue = myURLClassLoader
          .loadClass("com.xianyue.util.json.JackSonMain");
      Object obj = constValue.newInstance();

      Method[] methods = obj.getClass().getDeclaredMethods();
      for (Method method : methods) {
        System.out.println(method.getName());
        if (method.getName().equals("objectMapperTest")) {
          method.setAccessible(true);
          method.invoke(obj);
        }
      }
      System.out.println("obj1 --> " + obj);
      System.out.println(obj.getClass().getClassLoader());
      myURLClassLoader = null; //回收加载器

      MyURLClassLoader myURLClassLoader2 = new MyURLClassLoader();
      myURLClassLoader2.setDir(System.getProperty("user.dir") + "/jvmlearn/src/main/resources/tempclass");
      Class<?> constValue2 = myURLClassLoader2.loadClass("com.xianyue.util.json.JackSonMain");
      Object obj2 = constValue.newInstance();
      System.out.println("obj2 --> " + obj2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
