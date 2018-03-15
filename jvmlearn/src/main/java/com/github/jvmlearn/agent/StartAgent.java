package com.github.jvmlearn.agent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * java -javaagent:./jvmlearn-1.0-SNAPSHOT.jar=s1 -javaagent:./jvmlearn-1.0-SNAPSHOT.jar=s2 -jar
 * ownJar.jar
 *
 * 其中jar后面的包可以是任意包，我们通过-javaagent参数来使用我们自定义的javaagent类
 *
 *
 * 注意： 这个方法是在Main执行之前执行，如果我们需要在main方法执行后在执行代理方法，那么我们需要使用agentmain(String,Instrumentation)
 * ,同时需要在主程序中添加配置Agent-Class
 */
public class StartAgent {

    public static void premain(String agentParam, Instrumentation instrumentation) {
        System.out.println("premain --> First Step");
        System.out.println(agentParam);
        Arrays.asList(instrumentation.getAllLoadedClasses()).forEach(t -> System.out.println(t.getName()));
    }

    /**
     * 如果premain(string, Instrumentation) 不存在，那么执行该方法
     */
    public static void premain(String agentParam) {
        System.out.println("premain --> First Step no instrumentation");
        System.out.println(agentParam);
    }

}
