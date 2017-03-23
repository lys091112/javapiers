package com.xianyue.mocktiotest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import static org.mockito.Mockito.*;

import java.io.File;

import static org.junit.Assert.*;

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

}
