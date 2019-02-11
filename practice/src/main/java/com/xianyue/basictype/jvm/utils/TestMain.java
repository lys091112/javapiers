package com.xianyue.basictype.jvm.utils;

import com.google.common.base.Joiner;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author liuhongjun
 * @since 下午5:36 18-11-23
 */
public class TestMain<T extends Number & Cloneable> {

    private List<T> list = new ArrayList<>();
    private Set<T> set = null;

    private List<String>[] arrays = null;

    private List<? extends Number> numbers = null;

    public static void main(String[] args) throws NoSuchFieldException {
        TestMap maps = new TestMap();
        testWildCardType();

    }


    public static void testWildCardType() throws NoSuchFieldException {
        System.out.println("-----------WildCardType-----------------");
        Field field = TestMain.class.getDeclaredField("numbers");
        field.setAccessible(true);
        Type type = field.getGenericType();
        System.out.println("typeName=" + type.getClass().getName());

        // 获取实际的参数类型
        Type realType = ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println("realType name=" + realType.getClass().getName());

        WildcardType wildcardType = (WildcardType) realType;
        System.out.println("Upper Bounds");
        Stream.of(wildcardType.getUpperBounds()).forEach(bound -> System.out.println("\t--" + bound));

        System.out.println("Low Bounds");
        Stream.of(wildcardType.getLowerBounds()).forEach(bound -> System.out.println("\t--" + bound));

    }

    private static void testGenericArrayType() throws NoSuchFieldException {
        System.out.println("-----------GenericArrayType-----------------");
        Field field = TestMain.class.getDeclaredField("arrays");
        field.setAccessible(true);
        Type type = field.getGenericType();
        System.out.println("typeName=" + type.getClass().getName());

        GenericArrayType arrayType = (GenericArrayType) type;

        // GenericArrayType 中嵌套的ParameterizedType
        Type componentType = arrayType.getGenericComponentType();
        System.out.println("componentType=" + componentType.getClass().getName());
        ParameterizedType param = (ParameterizedType) arrayType.getGenericComponentType();
        System.out.println(param.getOwnerType());
        System.out.println(param.getRawType());
        System.out.println(Joiner.on(",").join(param.getActualTypeArguments()));

        System.out.println(arrayType.getGenericComponentType());
    }


    private static void testParameterizedType() throws NoSuchFieldException {
        System.out.println("-----------ParameterizedType-------------");
        Field field = TestMain.class.getDeclaredField("list");
        field.setAccessible(true);
        Type type = field.getGenericType();
        System.out.println(type.getClass().getName());

        ParameterizedType param = (ParameterizedType) type;
        System.out.println(param.getOwnerType());
        System.out.println(param.getRawType());
        System.out.println(Joiner.on(",").join(param.getActualTypeArguments()));

        Type[] realArguments = param.getActualTypeArguments();
        System.out.println("realArgument Type=" + realArguments[0].getClass().getName());

        TypeVariable var = (TypeVariable) realArguments[0];
        System.out.println("TypeVariable Name=" + var.getName());
        System.out.println("TypeVariable Bounds=");
        // 获得该类型变量的上限，也就是泛型中extend右边的值 获取的Bounds来源与主类中的T的限制
        Stream.of(var.getBounds()).forEach(bound -> System.out.println("\t--" + bound));
        System.out.println("TypeVariable AnnationBound=" + var.getAnnotatedBounds());
        System.out.println("TypeVariable GenericDeclaration=" + var.getGenericDeclaration());


    }

    private static class TestMap extends ConcurrentHashMap<String, List<String>> {

    }


}
