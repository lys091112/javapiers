package com.xianyue.langfeature.java8;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 */
public class Base64s {

    public static void main(String[] args) {
       String text = "this is a base64 demo";

       String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("encode: " + encode);

        String decode = new String(Base64.getDecoder().decode(encode));
        System.out.println(decode);

    }
}
