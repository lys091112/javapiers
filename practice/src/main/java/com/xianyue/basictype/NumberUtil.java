package com.xianyue.basictype;

/**
 * @author Xianyue
 */
public class NumberUtil {
    private static void basic() {
        double r = 1e-15;
        System.out.println(r);
    }

    public static void longBasic() {
        System.out.println("------------Long Basic------------");
        System.out.println(Long.MAX_VALUE);

        System.out.println(Long.MAX_VALUE / 6000000.0 / 24 / 3600 / 365);
    }

    public static void main(String[] args) {
        basic();
        longBasic();
    }
}
