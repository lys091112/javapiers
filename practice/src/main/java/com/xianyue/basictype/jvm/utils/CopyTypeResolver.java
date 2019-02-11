package com.xianyue.basictype.jvm.utils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Map;

/**
 * @author liuhongjun
 * @since 下午4:48 18-11-23
 */
public class CopyTypeResolver {

    public static Map<TypeVariableKey, Type> introspector(Type type) {
        TypeMappingIntrospector r = new TypeMappingIntrospector();
        r.visit(type);
        return r.mappings;
    }

    private static final class TypeMappingIntrospector extends CopyTypeVisitor {

        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        /**
         * Returns type mappings using type parameters and type arguments found in the generic superclass and the super interfaces of {@code
         * contextClass}.
         */
        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            Preconditions.checkNotNull(contextType);
            TypeMappingIntrospector introspector = new TypeMappingIntrospector();
            introspector.visit(contextType);
            return ImmutableMap.copyOf(introspector.mappings);
        }

        @Override
        void visitClass(Class<?> clazz) {
            visit(clazz.getGenericSuperclass());
            visit(clazz.getGenericInterfaces());
        }

        @Override
        void visitParameterizedType(ParameterizedType parameterizedType) {
            Class<?> rawClass = (Class<?>) parameterizedType.getRawType();
            TypeVariable<?>[] vars = rawClass.getTypeParameters();
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(vars.length == typeArgs.length);
            for (int i = 0; i < vars.length; i++) {
                map(new TypeVariableKey(vars[i]), typeArgs[i]);
            }
            visit(rawClass);
            visit(parameterizedType.getOwnerType());
        }

        @Override
        void visitTypeVariable(TypeVariable<?> t) {
            visit(t.getBounds());
        }

        @Override
        void visitWildcardType(WildcardType t) {
            visit(t.getUpperBounds());
        }

        private void map(final TypeVariableKey var, final Type arg) {
            if (mappings.containsKey(var)) {
                // Mapping already established
                // This is possible when following both superClass -> enclosingClass
                // and enclosingclass -> superClass paths.
                // Since we follow the path of superclass first, enclosing second,
                // superclass mapping should take precedence.
                return;
            }
            // First, check whether var -> arg forms a cycle
            for (Type t = arg; t != null; t = mappings.get(TypeVariableKey.forLookup(t))) {
                if (var.equalsType(t)) {
                    // cycle detected, remove the entire cycle from the mapping so that
                    // each type variable resolves deterministically to itself.
                    // Otherwise, a F -> T cycle will end up resolving both F and T
                    // nondeterministically to either F or T.
                    for (Type x = arg; x != null; x = mappings.remove(TypeVariableKey.forLookup(x))) {
                    }
                    return;
                }
            }
            mappings.put(var, arg);
        }
    }

    static final class TypeVariableKey {

        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> var) {
            this.var = Preconditions.checkNotNull(var);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(var.getGenericDeclaration(), var.getName());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                TypeVariableKey that = (TypeVariableKey) obj;
                return equalsTypeVariable(that.var);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return var.toString();
        }

        /**
         * Wraps {@code t} in a {@code TypeVariableKey} if it's a type variable.
         */
        static TypeVariableKey forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable<?>) t);
            } else {
                return null;
            }
        }

        /**
         * Returns true if {@code type} is a {@code TypeVariable} with the same name and declared by the same {@code GenericDeclaration}.
         */
        boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable<?>) type);
            } else {
                return false;
            }
        }

        private boolean equalsTypeVariable(TypeVariable<?> that) {
            return var.getGenericDeclaration().equals(that.getGenericDeclaration())
                && var.getName().equals(that.getName());
        }
    }
}
