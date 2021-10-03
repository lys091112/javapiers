package me.crescent.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author liuhongjun
 * @since 2019-11-17
 */
public class StartUp {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain handler");
        System.out.println("----------------------------premain-----------------");
        instrumentation.addTransformer(new OClassTransformer());
    }

}
