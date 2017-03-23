# 正则表达式的使用规范
---------------

> java的正则表达式的一般流程是为字符串的Java正则表达式首先实现被编译为pattern类的实例。然后，可将得到的模式用于创建 Matcher 对象，依照Java正则表达式，该对象可以与任意字符序列匹配。执行匹配所涉及的所有状态都驻留在匹配器中，所以多个匹配器可以共享同一模式

```java
Pattern p = Pattern.compile("a*b); //匹配模式
Matcher m = p.matcher("aaaab); //匹配Mather类
boolean flag = m.matches()  //匹配结果

//也可以这样表达，只是没有办法复用对象
boolean flag = Pattern.matches("a*b","aaaab");
```

## 类说明
### Pattern
该实例类是不可变的，可以供多个线程并发使用

## Macther
不可以被多个线程同时使用，非线程安全.该实例有3种不同的使用方式：

1. matches   方法尝试将整个输入序列与该模式匹配。 (注:当调用String的matches()方法时,实际上是调用Pattern的静态方法matches().也就是相当于调Matcher的matches(),所以是整个输入序列与模式匹配.)
2. lookingAt  尝试将输入序列从头开始与该模式匹配。
3. find     方法扫描输入序列以查找与该模式匹配的下一个子序列


## 正则表达式使用实例







