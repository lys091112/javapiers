package com.xianyue.basictype;

import java.math.BigDecimal;
import lombok.experimental.UtilityClass;

/**
 * @author Xianyue
 */
@UtilityClass
public class NumberUtil {

    private void basic() {
        double r = 1e-15;
        System.out.println(r);
    }


    public void longBasic() {
        System.out.println("------------Long Basic------------");
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MAX_VALUE / 6000000.0 / 24 / 3600 / 365);
    }

    public void scientificNotation() {
        System.out.println("科学计数法数字");
        double num1 = 50123.12E23;
        System.out.println(num1);
        BigDecimal bd1 = new BigDecimal(num1);
        System.out.println(bd1.toPlainString());
        System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        System.out.println("普通数字");
        double num2 = 50123.12;
        System.out.println(num2);
        BigDecimal bd2 = new BigDecimal(num2);
        System.out.println(bd2.toPlainString());
        System.out.println(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        // 通过这样可以将科学计数法转化为真实的数字对应的字符串
        System.out.println("--------");
        String k = String.valueOf((double) num1);
        System.out.println(k);
    }

    /**
     * 将16进制的字符串转化为二进制的字符串
     */
    public String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16)); // tmp的作用是为了填充字符串
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    public static void main(String[] args) {
        basic();
        longBasic();
        scientificNotation();

        // 将二进制字符串转化为整形
        System.out.println(Integer.parseInt("1101", 2));
    }
}


