package com.xianyue.classloader.instrument;

import java.lang.instrument.Instrumentation;

/**
 * TODO need to complete this demo work
 */
public class Agent {

    public static void premain(String arg, Instrumentation inst) {
        System.out.println("Hi, I'm agent!");
        inst.addTransformer(new TestClassTransformer());
    }
}
