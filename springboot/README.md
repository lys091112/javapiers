# 使用记录

 ##启动
 运行gradle bootRun可以通过控制台启动sprintboot


## 记录错误

1. 在boot+mybatis过程中，不断报错如下：
Caused by: java.lang.annotation.AnnotationFormatError: Invalid default: public abstract java.lang.Class org.mybatis.spring.annotation.MapperScan.factoryBean()
	at java.lang.reflect.Method.getDefaultValue(Method.java:611) ~[na:1.8.0_45]

原因： 没有引用正确的spring-boot jdbc包，而引用的是spring-boot web包造成了mapperScan加载失败
