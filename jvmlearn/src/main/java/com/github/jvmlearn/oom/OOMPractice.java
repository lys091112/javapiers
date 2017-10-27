package com.github.jvmlearn.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * �ڴ�һ������չʾ
 *
 * VM Args��-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * ��1.7֮��û�������ô�����Ϊʹ�ö����ڴ�-XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * �����ڴ�ķ���: ����Ϣ����������̬������JIT�����������Ĵ��룬ֻ������Ϣ��Classes Metadata���Ƿŵ�Metaspace�ˣ����������ݣ������ŵ���Java����
 *
 *
 */
public class OOMPractice {

    public static void main(String[] args) throws Throwable {
//        heamOom();
//        stackOom();
        dircteMemoryOom();
    }

    static class HeapOom {

    }

    /**
     * ���ڴ����
     * VM Args��-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError //�������ɵ�ǰ�ѿ���
     */
    private static void heamOom() {
        List<HeapOom> list = new ArrayList<HeapOom>();
        int i = 0;
        while (i < 1000000) {
            list.add(new HeapOom());
            i++;
        }
    }

    static class StackOom {
        private long len = 0;

        public void stackLeak() {
            len++;
            stackLeak();
        }
    }

    /**
     * stack Leak
     * -Xss128k
     */
    private static void stackOom() throws Throwable {
       StackOom stackOom = new StackOom();

       try {
           stackOom.stackLeak();
       }catch (Throwable a) {
           System.out.println("length --" + stackOom.len);
           throw a;
       }
    }

    /**
     * VM Args-Xmx20M -XX:MaxDirectMemorySize=10M
     */
    private static void dircteMemoryOom() throws IllegalAccessException {
        long _1MB = 1024 * 1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        int i = 0;
        while (i < 1000000) {
            unsafe.allocateMemory(_1MB);
            i++;
        }
    }

}
