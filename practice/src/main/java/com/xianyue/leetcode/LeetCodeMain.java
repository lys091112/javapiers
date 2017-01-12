package com.xianyue.leetcode;

/**
 * @author  Xianyue
 */
public class LeetCodeMain {

    public static void main(String[] args) {
        HexConversion conversion = new HexConversion();
        String result = conversion.convertToN(100,5);
        System.out.println(result);
        System.out.println(conversion.convertToN(16,15));
        System.out.println(conversion.convertToN(29,30));
    }
}
