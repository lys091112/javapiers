package me.crescent.agent;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author liuhongjun
 * @since 2019-11-17
 */
public interface Handler {

    boolean isValid(String className);

    byte[] handle(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
        byte[] classfileBuffer) throws IllegalClassFormatException;


}
