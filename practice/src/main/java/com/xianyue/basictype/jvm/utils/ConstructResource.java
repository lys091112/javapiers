package com.xianyue.basictype.jvm.utils;

import com.xianyue.copylearn.beauty.ConfigException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 用于演示类的初始化操作
 *  copy from : kafka
 */
public class ConstructResource {

    public <T> T getInstance(String key, Class<T> t) throws ClassNotFoundException, ConfigException {
        Class<T> c = initial(key);
        if (c == null) {
            return null;
        }

        Object o = newInstance(c);
        if (!t.isInstance(o)) {
            throw new ConfigException(c.getName() + " is not an instance of " + t.getName());
        }
        return t.cast(o);
    }

    /**
     * Instantiate the class
     */
    public static <T> T newInstance(Class<T> c) throws ConfigException {
        if (c == null) {
            throw new ConfigException("class cannot be null");
        }
        try {
            return c.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new ConfigException("Could not find a public no-argument constructor for " + c.getName(), e);
        } catch (ReflectiveOperationException | RuntimeException e) {
            throw new ConfigException("Could not instantiate class " + c.getName(), e);
        }
    }

    public Class initial(String key) throws ClassNotFoundException {
        return Class.forName(key.trim(), true, getContextOrMainClassLoader());
    }

    public static ClassLoader getContextOrMainClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            return ConstructResource.class.getClassLoader();
        } else {
            return cl;
        }
    }

    /**
     * Parse a value according to its expected type.
     *
     * @param name The config name
     * @param value The config value
     * @param type The expected type
     * @return The parsed object
     */
    public static Object parseType(String name, Object value, Type type) throws ConfigException {
        try {
            if (value == null) {
                return null;
            }

            String trimmed = null;
            if (value instanceof String) {
                trimmed = ((String) value).trim();
            }

            switch (type) {
                case BOOLEAN:
                    if (value instanceof String) {
                        if (trimmed.equalsIgnoreCase("true")) {
                            return true;
                        } else if (trimmed.equalsIgnoreCase("false")) {
                            return false;
                        } else {
                            throw new ConfigException(name, value, "Expected value to be either true or false");
                        }
                    } else if (value instanceof Boolean) {
                        return value;
                    } else {
                        throw new ConfigException(name, value, "Expected value to be either true or false");
                    }
                case STRING:
                    if (value instanceof String) {
                        return trimmed;
                    } else {
                        throw new ConfigException(name, value,
                            "Expected value to be a string, but it was a " + value.getClass().getName());
                    }
                case INT:
                    if (value instanceof Integer) {
                        return value;
                    } else if (value instanceof String) {
                        return Integer.parseInt(trimmed);
                    } else {
                        throw new ConfigException(name, value,
                            "Expected value to be a 32-bit integer, but it was a " + value.getClass().getName());
                    }
                case SHORT:
                    if (value instanceof Short) {
                        return value;
                    } else if (value instanceof String) {
                        return Short.parseShort(trimmed);
                    } else {
                        throw new ConfigException(name, value,
                            "Expected value to be a 16-bit integer (short), but it was a " + value.getClass()
                                .getName());
                    }
                case LONG:
                    if (value instanceof Integer) {
                        return ((Integer) value).longValue();
                    }
                    if (value instanceof Long) {
                        return value;
                    } else if (value instanceof String) {
                        return Long.parseLong(trimmed);
                    } else {
                        throw new ConfigException(name, value,
                            "Expected value to be a 64-bit integer (long), but it was a " + value.getClass().getName());
                    }
                case DOUBLE:
                    if (value instanceof Number) {
                        return ((Number) value).doubleValue();
                    } else if (value instanceof String) {
                        return Double.parseDouble(trimmed);
                    } else {
                        throw new ConfigException(name, value,
                            "Expected value to be a double, but it was a " + value.getClass().getName());
                    }
                case LIST:
                    if (value instanceof List) {
                        return value;
                    } else if (value instanceof String) {
                        if (trimmed.isEmpty()) {
                            return Collections.emptyList();
                        } else {
                            return Arrays.asList(trimmed.split("\\s*,\\s*", -1));
                        }
                    } else {
                        throw new ConfigException(name, value, "Expected a comma separated list.");
                    }
                case CLASS:
                    if (value instanceof Class) {
                        return value;
                    } else if (value instanceof String) {
                        return Class.forName(trimmed, true, getContextOrMainClassLoader());
                    } else {
                        throw new ConfigException(name, value, "Expected a Class instance or class name.");
                    }
                default:
                    throw new IllegalStateException("Unknown type.");
            }
        } catch (NumberFormatException e) {
            throw new ConfigException(name, value, "Not a number of type " + type);
        } catch (ClassNotFoundException e) {
            throw new ConfigException(name, value, "Class " + value + " could not be found.");
        }
    }

    public enum Type {
        BOOLEAN,
        STRING,
        INT,
        SHORT,
        LONG,
        DOUBLE,
        LIST,
        CLASS
    }
}
