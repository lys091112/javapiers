package com.xianyue.mocktiotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * @author Xianyue
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Step.class, ClassDependcy.class})
public class StepTest {

    @InjectMocks
    Step step = new Step();         //需要手动new对象

    /**
     * 普通Mock不需要加@RunWith和@PrepareForTest注解。
     */
    @Test
    public void testCallArgumentInstance() {
        File file = PowerMockito.mock(File.class);
        PowerMockito.when(file.exists()).thenReturn(true);
//        step.callArgumentInstance(file);
        assertTrue(step.callArgumentInstance(file));
        Mockito.verify(file, times(1)).exists();
    }

    /**
     * 当使用PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith
     * 。注解@PrepareForTest里写的类是需要mock的new对象代码所在的类
     */
    @Test
    public void testCallInternalInstance() throws Exception {
        File file = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        assertTrue(step.callInternalInstance("bbb"));
        Mockito.verify(file, times(1)).exists();

    }

    /**
     * 当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。
     * 注解@PrepareForTest里写的类是final方法所在的类。
     */
    @Test
    public void testCallFinalMethod() {
        ClassDependcy cdcy = PowerMockito.mock(ClassDependcy.class);
        when(cdcy.isAlive()).thenReturn(false);
        assertFalse(step.callFinalMethod(cdcy));
    }


    @Test
    public void testCallStaticMethod() {
        PowerMockito.mockStatic(ClassDependcy.class);
        when(ClassDependcy.isExist()).thenReturn(true);
        assertTrue(step.callStaticMethod());
    }

    @Test
    public void testCallPrivateMethod() throws Exception {
        Step step1 = PowerMockito.spy(new Step());
        PowerMockito.when(step1, "isExist").thenReturn(true);
        assertTrue(step1.callPrivateMethod());
        PowerMockito.verifyPrivate(step1).invoke("isExist");
    }

    @Test
    public void testCallSystemMethod() {
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
        assertEquals("bbb", step.callSystemStaticMethod("aaa"));
    }


    /**
     *<p>
     *     用来精确mock函数执行时的参数.
     *     如下： 表示当argument=5是测试才可以通过
     *</p>
     */
    @Test
    public void testAA() {
        List list = Mockito.mock(ArrayList.class);
        when(list.add(Mockito.argThat(new ArgumentMatcher<Integer>() {
            @Override
            public boolean matches(Object argument) {
                return ((int) argument) == 5;
            }
        }))).thenReturn(true);

        boolean res = list.add(5);
        assertTrue(res);
    }

    @Test
    public void testBB() {
        List list = Mockito.mock(ArrayList.class);
        when(list.add(Mockito.eq(5))).thenReturn(true);

        boolean res = list.add(5);
        assertTrue(res);
        boolean res2 = list.add(3);
        assertFalse(res2);
    }
}
