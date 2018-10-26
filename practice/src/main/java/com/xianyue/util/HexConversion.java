package com.xianyue.util;

/**
 * @author Xianyue 进制转换
 */
public class HexConversion {

    char[] index = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                    'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     *  将十进制的数转化为n进制的数，但是n进制不能超过index的长度，不然无法用字符表示
     */
    public String convertToN(int number, int n) {
        if (number <= 0 || n <= 0 || n >= index.length) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int p = number;
        int r; // 用于记录余数
        while (p > 0) {
            r = p % n;
            p = p / n;
            sb.append(index[r]);
        }
        return sb.reverse().toString();
    }
}
