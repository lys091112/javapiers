package com.xianyue.instrument;

import javax.sound.midi.Instrument;
import java.lang.instrument.Instrumentation;

/**
 * @author  Xianyue
 */
public class Agent {

    public static void premain(String arg, Instrumentation inst) {
        System.out.println("Hi, I'm agent!");
        inst.addTransformer(new TestClassTransformer());
    }
}
