package com.xianyue.basictype;

/**
 * @author Xianyue
 */
public class BitUtil {

    public static boolean isBitFit(int param, int ordinal) {
        return (param & (1 << ordinal - 1)) > 0;
    }

    public static void main(String[] args) {
        boolean ret = isBitFit(7, 2);
        System.out.println("result is " + ret);
    }
}
