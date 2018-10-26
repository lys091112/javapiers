package com.github.jvmlearn.utils;

import com.google.common.base.Joiner;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * copy from arthas https://github.com/alibaba/arthas
 */
public class TypeRenderUtils {

    public static String drawAnnotation(Class<?> clazz) {
        return drawAnnotation(clazz.getDeclaredAnnotations());
    }

    public static String drawAnnotation(Method method) {
        return drawAnnotation(method.getDeclaredAnnotations());
    }

    public static String drawInterface(Class<?> clazz) {
        return Joiner.on(",").join(clazz.getInterfaces());
    }

    public static String drawParameters(Method method) {
        return Joiner.on("\n").join(method.getParameterTypes());
    }

    public static String drawParameters(Constructor constructor) {
        return Joiner.on("\n").join(constructor.getParameterTypes());
    }

    public static String drawReturn(Method method) {
        return classname(method.getReturnType());
    }

    public static String drawExceptions(Method method) {
        return Joiner.on("\n").join(method.getExceptionTypes());
    }

    public static String drawExceptions(Constructor constructor) {
        return Joiner.on("\n").join(constructor.getExceptionTypes());
    }

    public static String drawSuperClass(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();

        Class<?> superClass = clazz.getSuperclass();
        if (null != superClass) {
            sb.append("+-").append(classname(superClass)).append("\n").append("  ");

            while (true) {
                superClass = superClass.getSuperclass();
                if (null == superClass) {
                    break;
                }
                sb.append("+-").append(classname(superClass)).append("\n").append("  ");
            }
        }
        return sb.toString();
    }

    private static String buildTree(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (String str : names) {
            sb.append("+-").append(str).append("\n").append("  ");
        }
        return sb.toString();
    }

    public static String drawClassLoader(Class<?> clazz) {
        List<String> links = new LinkedList<>();
        ClassLoader loader = clazz.getClassLoader();
        if (null != loader) {
            links.add(loader.toString());
            while (true) {
                loader = loader.getParent();
                if (null == loader) {
                    break;
                }
                links.add(loader.toString());
            }
        }
        return buildTree(links);
    }

    public static String drawField(Class<?> clazz, Integer deep) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return "";
        }

        for (Field field : fields) {
            sb.append("modifier    " + modifier(field.getModifiers(), ',')).append("\n")
                .append("type    ").append(classname(field.getType())).append("\n")
                .append("name    ").append(field.getName()).append("\n");

            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length > 0) {
                sb.append("annotation    ").append(drawAnnotation(annotations)).append("\n");
            }

            if (Modifier.isStatic(field.getModifiers())) {
                sb.append("value    ").append(drawFieldValue(field, deep)).append("\n");
            }

        }

        return sb.toString();
    }

    public static String renderMethodSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(modifier(method.getModifiers(), ' ')).append(" ");
        sb.append(TypeRenderUtils.drawReturn(method)).append(" ");
        sb.append(method.getName()).append(" ");
        sb.append("(");
        sb.append(Joiner.on(", ").join(method.getParameterTypes()));
        sb.append(")");
        return sb.toString();
    }

    private static String drawFieldValue(Field field, Integer expand) {
        final boolean isAccessible = field.isAccessible();
        try {
            field.setAccessible(true);
            Object value = field.get(null);
//            return value.toString();
            return "";
        } catch (IllegalAccessException e) {
            // no op
        } finally {
            field.setAccessible(isAccessible);
        }
        return "";
    }

    public static String drawAnnotation(Annotation... annotations) {
        List<Class<?>> types = Collections.emptyList();
        if (annotations != null && annotations.length > 0) {
            types = new LinkedList<Class<?>>();
            for (Annotation annotation : annotations) {
                types.add(annotation.annotationType());
            }
        }
        return Joiner.on(",").join(types.toArray(new Class<?>[types.size()]));
    }

    public static String classname(Class<?> clazz) {
        if (clazz.isArray()) {
            StringBuilder sb = new StringBuilder(clazz.getName());
            sb.delete(0, 2);
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ';') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("[]");
            return sb.toString();
        } else {
            return clazz.getName();
        }
    }

    public static String modifier(int mod, char splitter) {
        StringBuilder sb = new StringBuilder();
        if (Modifier.isAbstract(mod)) {
            sb.append("abstract").append(splitter);
        }
        if (Modifier.isFinal(mod)) {
            sb.append("final").append(splitter);
        }
        if (Modifier.isInterface(mod)) {
            sb.append("interface").append(splitter);
        }
        if (Modifier.isNative(mod)) {
            sb.append("native").append(splitter);
        }
        if (Modifier.isPrivate(mod)) {
            sb.append("private").append(splitter);
        }
        if (Modifier.isProtected(mod)) {
            sb.append("protected").append(splitter);
        }
        if (Modifier.isPublic(mod)) {
            sb.append("public").append(splitter);
        }
        if (Modifier.isStatic(mod)) {
            sb.append("static").append(splitter);
        }
        if (Modifier.isStrict(mod)) {
            sb.append("strict").append(splitter);
        }
        if (Modifier.isSynchronized(mod)) {
            sb.append("synchronized").append(splitter);
        }
        if (Modifier.isTransient(mod)) {
            sb.append("transient").append(splitter);
        }
        if (Modifier.isVolatile(mod)) {
            sb.append("volatile").append(splitter);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String classLoaderHash(Class<?> clazz) {
        if (clazz == null || clazz.getClassLoader() == null) {
            return "null";
        }
        return Integer.toHexString(clazz.getClassLoader().hashCode());
    }
}
