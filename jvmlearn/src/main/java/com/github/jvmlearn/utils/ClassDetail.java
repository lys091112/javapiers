package com.github.jvmlearn.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @since 上午10:06 18-10-11
 */
@Setter
@Getter
@Accessors(chain = true)
@ToString
public class ClassDetail {

    private String classInfo;
    private String codeSource;
    private String name;
    private String isInterface;
    private String isAnnotation;
    private String isEnum;
    private String isAnonymousClass;
    private String isArray;
    private String isLocalClass;
    private String isMemberClass;
    private String isPrimitive;
    private String isSynthetic;
    private String simpleName;
    private String modifier;
    private String annotation;
    private String interfaces;
    private String superClass;
    private String classLoader;
    private String classLoaderHash;
    private String fileds;

}
