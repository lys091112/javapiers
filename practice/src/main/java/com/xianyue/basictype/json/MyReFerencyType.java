package com.xianyue.basictype.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

/** 获取范型类型 */
@Slf4j
public class MyReFerencyType<T> {

  private Type type;

  public MyReFerencyType(Function function) {
    Type superClass = getClass().getGenericSuperclass();

    type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    System.out.println(superClass);
    System.out.println(this.type);
    function.apply(type);

    System.out.println(((ParameterizedType) superClass).getRawType());
    System.out.println(((ParameterizedType) superClass).getOwnerType());
  }

  public static void main(String[] args) {
    Function<Type, Integer> function = type -> fetchMapValue(type);

    //建立一个匿名的类
    new MyReFerencyType<HashMap<String, Integer>>(function) {};
  }

  private static int fetchMapValue(Type type) {
    Type keyType = ((ParameterizedType) type).getActualTypeArguments()[0];
    Type valueType = ((ParameterizedType) type).getActualTypeArguments()[1];

    if (keyType == String.class) {
      System.out.println("type--> " + keyType.getTypeName());
    }
    if (valueType == Integer.class) {
      System.out.println("type--->" + valueType.getTypeName());
    }

    return 0;
  }
}
