package com.xianyue.basictype.file.path;

import com.xianyue.basictype.file.util.PathUtil;

/**
 * lass.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。 例如你写了一个MyTest类在包com.test.mycode 下，那么MyTest.class.getResourceAsStream("name")
 * 会在com.test.mycode包下查找相应的资源。 如果这个name是以 '/' 开头的，那么就会从classpath的根路径下开始查找。
 * <p>
 * ClassLoader.getResourceAsStream()  无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。 所以: MyTest.getClassLoader().getResourceAsStream("name") 和
 * MyTest.getClassLoader().getResourceAsStream("name") 的效果是一样的
 *
 * 总结一下，可能只是两种写法
 *
 * 第一：前面有 “   / ” / ”代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject me.class.getResourceAsStream("/com/x/file/myfile.xml");
 *
 * 第二：前面没有 “   / ” 代表当前类的目录 me.class.getResourceAsStream("myfile.xml"); me.class.getResourceAsStream("file/myfile.xml");
 *
 * 在不是使用第三方框架的情况下，程序加载配置文件的3种方式：
 *
 * 1. 使用绝对路径，在程序启动是通过传递系统变量如-Dconfig.dir来在运行时获取配置文件的路径
 *
 * 2. 在启动时添加进classpath， 如 java -cp 1.jar:./conf:./conf2 **.Main 那么JavaPathSample.class.getClassLoader().getResource("").getPath()默认获取的是./conf
 *
 * 3. 在启动时添加进classpath， 如 java -cp 1.jar  **.Main ,不传递配置文件路径，那么JavaPathSample.class.getClassLoader().getResource("").getPath()默认会产生空异常，此时需要
 * 添加jar中的配置文件，以便于查找的顺利进行，如JavaPathSample.class.getClassLoader().getResource("wtab.txt").getPath(), 如果文件在jar包中，那么需要通过ResourceAsStream来获取
 *
 * 4. 包含在Jar包中的资源文件本身不是一个文件，所以FileInputStream不能够直接读取Jar中文件的内容，需要通过ResourceAsStream来获取，如果依赖的第三方程序无法使用ResourceAsStream,那么可以
 * 先通过ResourceAsStream生成临时文件，然后在让第三方程序加载生成的临时文件解决 参考： CreatePath.java
 */
public class JavaPathSample {


    public static void main(String[] args) {
        //获取文件当前的路径，不包括自己
        System.out.println("single-1" + JavaPathSample.class.getResource("").getPath());

        //获取当前classPath的绝对URL路
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("single-2" + JavaPathSample.class.getClassLoader().getResource("").getPath());
        System.out.println("single-3" + JavaPathSample.class.getResource("/").getPath());
        System.out.println("single-4" + ClassLoader.getSystemResource("").getPath());
        System.out.println("single-5" + Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println("single-5" + JavaPathSample.class.getClassLoader().getClass().getName());
        System.out.println("single-6" + Thread.currentThread().getContextClassLoader().getClass().getName());

        // 获取jar包中的文件流
//        InputStream InputStream = JavaPathSample.class.getClassLoader().getResourceAsStream("wtable.properties");
        // 用于获取jar中的文件路径
//        String filePath = JavaPathSample.class.getClassLoader().getResource("wtable.properties").getPath();

        System.out.println(PathUtil.currentClassPath(null));
    }


}
