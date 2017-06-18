package com.xianyue.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * @author Xianyue
 */
public class Md5Generator {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String digest(String text) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(text.getBytes(Charset.forName("utf-8")));
            byte[] md = mdInst.digest();
            return encodeHex(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String encodeHex(byte[] md) {
        int len = md.length;
        char[] chars = new char[len * 2];
        int k = 0;
        for (byte b : md) {
            chars[k++] = hexDigits[b >>> 4 & 0xf];
            chars[k++] = hexDigits[b & 0xf];
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(digest("hello"));
        System.out.println("5D41402ABC4B2A76B9719D911017C592");
    }
}
