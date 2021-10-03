package me.crescent.agent;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import me.crescent.agent.idea.MarkdownPluginHandler;

/**
 * @author liuhongjun
 * @since 2019-11-17
 */
public class OClassTransformer implements ClassFileTransformer {

    Handler markdownPlugin = new MarkdownPluginHandler();

    @Override
    public byte[] transform(
        ClassLoader loader,
        String className,
        Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain,
        byte[] classfileBuffer)
        throws IllegalClassFormatException {
        className = className.replaceAll("/", ".");
        System.out.println("loader=" + loader.toString() + ", className=" + className);
        if (markdownPlugin.isValid(className)) {
            return markdownPlugin.handle(
                loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
        }

        if ("com.xianyue.Main".equalsIgnoreCase(className)) {
            try {
                CtClass ctclass = ClassPool.getDefault().get(className);
                CtMethod[] methods = ctclass.getMethods();
                for (CtMethod method : methods) {
                    if (!"test".equalsIgnoreCase(method.getName())) {
                        continue;
                    }
                    String methodName = method.getName();
                    method.setName(methodName + "$old");
                    CtMethod newMethod = CtNewMethod.copy(method, methodName, ctclass, null);
                    StringBuilder bodyStr = new StringBuilder();
                    bodyStr.append("{");
                    bodyStr.append("System.out.println(\"-----------------------\");");
                    bodyStr.append(methodName + "$old" + "($$);");
                    bodyStr.append("}");
                    System.out.println(bodyStr.toString());
                    newMethod.setBody(bodyStr.toString());
                    ctclass.addMethod(newMethod);// 增加新方
                }
                return ctclass.toBytecode();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
