package com.xianyue.mocktiotest;

import java.io.File;

/**
 * @author Xianyue
 */
public class Step {
    public boolean callArgumentInstance(File file) {
        return file.exists();
    }


    public boolean callInternalInstance(String path) {
        File file = new File(path);
        return file.exists();
    }


    public boolean callFinalMethod(ClassDependcy cdcy) {
       return cdcy.isAlive() ;
    }

    public boolean callStaticMethod() {
        return ClassDependcy.isExist();
    }

    public boolean callPrivateMethod() {
        return isExist();
    }

    private boolean isExist() {
        return true;
    }


    public boolean callSystemFinalMethod(String str) {
        return str.isEmpty();
    }


    public String callSystemStaticMethod(String str) {
        return System.getProperty(str);
    }

}
