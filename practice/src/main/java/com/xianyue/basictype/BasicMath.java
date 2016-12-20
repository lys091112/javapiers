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

    public static void main(String[] args) {
        BasicMath math = new BasicMath();
        math.complementation();
    }
}
