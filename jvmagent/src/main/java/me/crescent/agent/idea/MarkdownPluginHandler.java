package me.crescent.agent.idea;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import me.crescent.agent.Handler;

/**
 * @author liuhongjun
 * @since 2019-11-17
 *
 * // TODO 暂未解决
 */
public class MarkdownPluginHandler implements Handler {


    @Override
    public boolean isValid(String className) {
        return "com.vladsch.idea.multimarkdown.license.LicenseAgent".equalsIgnoreCase(className);
    }

    @Override
    public byte[] handle(
        ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
        byte[] classfileBuffer) throws IllegalClassFormatException {

        try {
            CtClass ctclass = ClassPool.getDefault().get(className);
            CtMethod[] methods = ctclass.getMethods();
            for (CtMethod method : methods) {
                String methodName = method.getName();
                CtMethod newMethod = CtNewMethod.copy(method, methodName, ctclass, null);
                StringBuilder bodyStr = new StringBuilder();
                bodyStr.append("{\n");
                if ("isRemoveLicense".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return false;");
                } else if ("getLicenseExpires".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return \"Never Expired\";");
                } else if ("licenseCode".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return \"dadada\";");
                } else if ("isValidLicense".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return true;");
                } else if ("isValidActivation".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return true;");
                } else if ("getLicenseCode".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return true;");
                } else if ("getLicenseType".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return \"license\";");
                } else if ("getLicenseFeatures".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return 4;");
                } else if ("getLicenseExpiration".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return \"9999-12-31\";");
                } else if ("getActivatedOn".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return \"2018-01-01\";");
                } else if ("getLicenseExpiringIn".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return " + Integer.MAX_VALUE + ";");
                } else if ("isActivationExpired".equalsIgnoreCase(method.getName())) {
                    bodyStr.append("return true;");
                } else {
                    continue;
                }
                method.setName(methodName + "$old");
                bodyStr.append("\n}");
                System.out.println("body=" + bodyStr.toString());
                newMethod.setBody(bodyStr.toString());
                ctclass.addMethod(newMethod);
            }
            return ctclass.toBytecode();
        } catch (Exception e) {
            System.out.println("premain: idea markdown error" + e.getMessage());
            e.printStackTrace();
            throw new IllegalClassFormatException(e.getMessage());
        }
    }

}
