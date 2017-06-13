package com.xianyue.file.path;

/**
 * lass.getResourceAsStream() 会指定要加载的资源路径与当前类所在包的路径一致。
 * 例如你写了一个MyTest类在包com.test.mycode 下，那么MyTest.class.getResourceAsStream("name") 会在com.test.mycode包下查找相应的资源。
 * 如果这个name是以 '/' 开头的，那么就会从classpath的根路径下开始查找。
 * <p>
 * ClassLoader.getResourceAsStream()  无论要查找的资源前面是否带'/' 都会从classpath的根路径下查找。
 * 所以: MyTest.getClassLoader().getResourceAsStream("name") 和
 * MyTest.getClassLoader().getResourceAsStream("name") 的效果是一样的
 *
 * 总结一下，可能只是两种写法
 * 第一：前面有 “   / ”
 * / ”代表了工程的根目录，例如工程名叫做myproject，“ / ”代表了myproject
 * me.class.getResourceAsStream("/com/x/file/myfile.xml");
 *
 * 第二：前面没有 “   / ”
 * 代表当前类的目录
 * me.class.getResourceAsStream("myfile.xml");
 * me.class.getResourceAsStream("file/myfile.xml");
 */
public class JavaPathSample {


    public static void main(String[] args) {
        //获取文件当前的路径，不包括自己
        System.out.println(JavaPathSample.class.getResource("").getPath());

        //获取当前classPath的绝对URL路
        System.out.println(JavaPathSample.class.getClassLoader().getResource("").getPath());
        System.out.println(JavaPathSample.class.getResource("/").getPath());
        System.out.println(ClassLoader.getSystemResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(JavaPathSample.class.getClassLoader().getClass().getName());
        System.out.println(Thread.currentThread().getContextClassLoader().getClass().getName());
    }
}
