package com.xianyue.basictype.jvm.utils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import com.google.common.collect.Sets;
import java.util.Set;

abstract class CopyTypeVisitor {

    private final Set<Type> visited = Sets.newHashSet();

    /**
     * Visits the given types. Null types are ignored. This allows subclasses to call {@code visit(parameterizedType.getOwnerType())} safely
     * without having to check nulls.
     */
    public final void visit(Type... types) {
        for (Type type : types) {
            if (type == null || !visited.add(type)) {
                // null owner type, or already visited;
                continue;
            }
            boolean succeeded = false;
            try {
                if (type instanceof TypeVariable) {
                    visitTypeVariable((TypeVariable<?>) type);
                } else if (type instanceof WildcardType) {
                    visitWildcardType((WildcardType) type);
                } else if (type instanceof ParameterizedType) {
                    visitParameterizedType((ParameterizedType) type);
                } else if (type instanceof Class) {
                    visitClass((Class<?>) type);
                } else if (type instanceof GenericArrayType) {
                    visitGenericArrayType((GenericArrayType) type);
                } else {
                    throw new AssertionError("Unknown type: " + type);
                }
                succeeded = true;
            } finally {
                if (!succeeded) { // When the visitation failed, we don't want to ignore the second.
                    visited.remove(type);
                }
            }
        }
    }

    void visitClass(Class<?> t) {
    }

    void visitGenericArrayType(GenericArrayType t) {
    }

    void visitParameterizedType(ParameterizedType t) {
    }

    void visitTypeVariable(TypeVariable<?> t) {
    }

    void visitWildcardType(WildcardType t) {
    }
}