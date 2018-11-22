package com.xianyue.basictype.jvm.agent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * java -javaagent:./jvmlearn-1.0-SNAPSHOT.jar=s1 -javaagent:./jvmlearn-1.0-SNAPSHOT.jar=s2 -jar
 * ownJar.jar
 *
 * 其中jar后面的包可以是任意包，我们通过-javaagent参数来使用我们自定义的javaagent类,这个也是java探针的实现技术
 *
 *
 *  启动之后在添加代理情况：
 * 注意： 这个方法是在Main执行之前执行，如果我们需要在main方法执行后在执行代理方法，那么我们需要使用agentmain(String,Instrumentation)
 * ,同时需要在主程序中添加配置Agent-Class
 *
 * 编写好agent jar之后，接下来需要将该jar挂接到目标进程的jvm中执行。
 * 由于agent main方式无法向pre main方式那样在命令行指定代理jar，
 * 因此需要借助Attach Tools API。使用com.sun.tools.attach包下的VirtualMachine类，
 * 使用attach pid 来得到相应的VirtumalMachine，使用loadAgent 方法指定AgentMain所在类并加载。
 * 其中com.sun.tools.attach.VirtualMachine的jar包是jdk下lib中的tools.jar
 * 例如：VirtualMachine vm = VirtualMachine.attach(targetPid);
 *
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
