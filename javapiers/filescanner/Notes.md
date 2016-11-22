# 项目记录

1. 加载class文件的两种不同方式的区别
 Class.forName("xxx"); //会默认初始化类的静态代码块，这样会造成有些类在加载时出现问题
 Thread.currentThread().getContextClassLoader().loadClass("xxx"); //不会加载类的静态代码块，那什么时候初始化？？？
