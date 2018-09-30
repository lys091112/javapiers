package com.xianyue.basictype.reflect;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/**
 * @since 上午11:05 18-8-9
 */

public class UnsafeLearn {

    //获取字节对象中非静态方法的偏移量(get offset of a non-static field in the object in bytes
    //public native long objectFieldOffset(java.lang.reflect.Field field);
    //获取数组中第一个元素的偏移量(get offset of a first element in the array)
    // public native int arrayBaseOffset(java.lang.Class aClass);
    //获取数组中一个元素的大小(get size of an element in the array)
    // public native int arrayIndexScale(java.lang.Class aClass);
    //获取JVM中的地址值(get address size for your JVM)
    //public native int addressSize();

    private static final Unsafe UNSAFE;

    static {
        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            };

            UNSAFE = AccessController.doPrivileged(action);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    public static void main(String[] args) {
        int scale = UNSAFE.arrayIndexScale(Object[].class);
        System.out.println("arrayScala " + scale);

        int offset = UNSAFE.arrayBaseOffset(Object[].class);
        System.out.println("offset=" + offset);

        System.out.println("address=" + UNSAFE.addressSize());

        // 偏移地址+占位大小×(i-1) = 第i个元素的起始地址
        int iscala = UNSAFE.arrayIndexScale(int[].class);
        int ioffset = UNSAFE.arrayBaseOffset(int[].class);

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        UNSAFE.putInt(arr, (long) (ioffset + iscala * 3), 0);

        for (int i = 0; i < 10; i++) {
            int t = UNSAFE.getInt(arr, (long) (ioffset + iscala * i));
            System.out.print(t + " ,");
        }
    }

}
