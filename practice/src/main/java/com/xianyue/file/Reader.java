package com.xianyue.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Xianyue
 * 考虑到读取文本文件时，不能太大，超出内存的限制。
 */
public class Reader {

    /**
     * 原始的使用readerbuffer读取文件的字节流方式
     * FileBufferReader
     * FileReader
     * @param filename
     */
    public static void readFileJDK6(String filename) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(new File("/home/langle/hello.txt"));
            fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {

                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *JDK7 使用nio读取文件的一种方式,但是无法制定字符集
     * @param filename
     */
    public static void readFileJDK7(String filename) {
        try {
            System.out.println(new String(Files.readAllBytes(Paths.get("build.gradle"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readFileJDK8(String filename) {
        try {
            Files.lines(Paths.get("build.gradle"), StandardCharsets.UTF_8).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFileJDK6("build.gradle");
        readFileJDK7("build.gradle");
        readFileJDK8("build.gradle");
    }
}
