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
     * 输入流（Input  Stream）： 程序从输入流读取数据源。数据源包括外界(键盘、文件、网络…)，即是将数据源读入到程序的通信通道
     * 输出流： 程序向输出流写入数据。将程序中的数据输出到外界（显示器、打印机、文件、网络…）的通信通道
     *
     * 采用数据流的目的就是使得输出输入独立于设备。
     * Input  Stream不关心数据源来自何种设备（键盘，文件，网络）
     * Output  Stream不关心数据的目的是何种设备（键盘，文件，网络）
     *
     *数据流分类：
     * 流序列中的数据既可以是未经加工的原始二进制数据，也可以是经一定编码处理后符合某种格式规定的特定数据。因此Java中的流分为两种：
     * 1)  字节流：数据流中最小的数据单元是字节
     * 2)  字符流：数据流中最小的数据单元是字符， Java中的字符是Unicode编码，一个字符占用两个字节
     */
    public static void readFileJDK6(String filename) {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(new File("/home/langle/hello.txt"));  //输出流，通过stream将数据输出到hello.txt文件中
            fis = new FileInputStream(filename);                        //输入流，从filename文件中获取文件数据，输入到stream中
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
        System.out.println("---------------jdk6");
        readFileJDK6("build.gradle");
        System.out.println("---------------jdk7");
        readFileJDK7("build.gradle");
        System.out.println("---------------jdk8");
        readFileJDK8("build.gradle");
    }
}
