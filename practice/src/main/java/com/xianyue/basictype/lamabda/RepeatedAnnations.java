package com.xianyue.basictype.lamabda;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class RepeatedAnnations {
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Filters {
    Filter[] value();
  }

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Repeatable(Filters.class)
  public @interface Filter {
    String value();
  }

  @Filter("fileter1")
  @Filter("fileter2")
  public interface FilterAble {}

  public static void main(String[] args) throws NoSuchMethodException {
    for (Filter filter : FilterAble.class.getAnnotationsByType(Filter.class)) {
      System.out.println(filter.value());
    }

    //获取方法参数名，这个需要其他的配置，使用-parameters参数compile
    Method method = RepeatedAnnations.class.getMethod("main", String[].class);
    for (final Parameter parameter : method.getParameters()) {
      System.out.println("--> " + parameter.getName());
    }
  }
}
