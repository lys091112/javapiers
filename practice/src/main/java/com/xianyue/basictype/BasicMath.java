package com.xianyue.basictype;

/**
 * @Xianyue  数学类相关的使用技巧
 */
public class BasicMath {

    private final int BUNKLE = 2048;
    /**
     *求余 a % b 在指令集上等价于 a - (a / b) * b
     * 1.当需要对2的次幂进行求余时，可以是使用&运算符来代替 公式为： a % x = a & (x - 1)
     */
    public void complementation() {
        //查看5086中包含多少个2048
        int size = ((5086  + BUNKLE - 1) & ~(BUNKLE -1)) / BUNKLE;
        System.out.println("size " + size);
    }

    /**
     *  牛顿迭代法求平方根
     */
    public static double sqrt(double a) {
        if(a < 0) return Double.NaN;
        double err = 1e-15;
        double t = a;
        while(Math.abs(t * t - a) > err * a) {
            t = (t + a / t) / 2.0;
        }
        return t;
    }

    /**
     * 调和级数
     */
    public static double harmonic(int n) {
        if(n < 0) return Double.NaN;
        double h = 0.0D;
        for (int i = 0; i < n; i++) {
           h += 1.0 / (i + 1) ;
        }
        return h;
    }

    public static void main(String[] args) {
        BasicMath math = new BasicMath();
        math.complementation();

        System.out.println(sqrt(5));
        System.out.println(harmonic(4));
    }
}
