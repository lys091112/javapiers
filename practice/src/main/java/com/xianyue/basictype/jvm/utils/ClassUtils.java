package com.xianyue.basictype.jvm.utils;

import static com.xianyue.basictype.jvm.utils.TypeRenderUtils.classname;
import static com.xianyue.basictype.jvm.utils.TypeRenderUtils.modifier;

import com.xianyue.basictype.jvm.HashMapNotThreadSafe;
import java.security.CodeSource;

/**
 * 类的工具类
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c = null;
        try {
            c = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            // do nothing
        }

        if (null == c) {
            // no thread classloader
            c = ClassUtils.class.getClassLoader();
            if (null == c) {
                // no class loader
                try {
                    c = ClassLoader.getSystemClassLoader();
                } catch (Exception e) {
                    // can't access system classloader
                }
            }
        }
        return c;
    }

    public static ClassDetail renderClassInfo(Class<?> clazz, boolean isPrintField) {
        ClassDetail detail = new ClassDetail();
        CodeSource cs = clazz.getProtectionDomain().getCodeSource();

        detail.setClassInfo(classname(clazz))
            .setCodeSource(getCodeSource(cs))
            .setName(classname(clazz))
            .setInterfaces("" + clazz.isInterface())
            .setIsAnnotation("" + clazz.isAnnotation())
            .setIsEnum("" + clazz.isEnum())
            .setIsAnonymousClass("" + clazz.isAnonymousClass())
            .setIsArray("" + clazz.isArray())
            .setIsLocalClass("" + clazz.isLocalClass())
            .setIsMemberClass("" + clazz.isMemberClass())
            .setIsPrimitive("" + clazz.isPrimitive())
            .setIsSynthetic("" + clazz.isSynthetic())
            .setSimpleName(clazz.getSimpleName())
            .setModifier(modifier(clazz.getModifiers(), ','))
            .setAnnotation(TypeRenderUtils.drawAnnotation(clazz))
            .setInterfaces(TypeRenderUtils.drawInterface(clazz))
            .setSuperClass(TypeRenderUtils.drawSuperClass(clazz))
            .setClassLoader(TypeRenderUtils.drawClassLoader(clazz))
            .setClassLoaderHash(TypeRenderUtils.classLoaderHash(clazz));

        if (isPrintField) {
            detail.setFileds(TypeRenderUtils.drawField(clazz, 0));
        }
        return detail;
    }

    private static String getCodeSource(final CodeSource cs) {
        if (null == cs || null == cs.getLocation() || null == cs.getLocation().getFile()) {
            return "";
        }

        return cs.getLocation().getFile();
    }

    public static void main(String[] args) {
        System.out.println(renderClassInfo(HashMapNotThreadSafe.class, true));
    }


}
