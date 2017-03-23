package com.xianyue.basictype;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Xianyue
 */
public class CmdUtil {

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("ping www.baidu.com",null, null);
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        reader.lines().forEach(t -> System.out.println(t));

    }
}
