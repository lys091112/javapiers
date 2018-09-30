package com.xianyue.file.path;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuhongjun
 * @since 下午4:01 18-6-25
 *
 * 由于包含在Jar包中的资源文件本身不是一个文件，所以FileInputStream不能够直接读取Jar中文件的内容， 而如果没有其他选择必须使用FileInputStream（比如你使用的第三方库就是使用FileInputStream， 需要你传递文件路径）的情况下，
 * 你可以考虑通过getResourceAsStream()先获取文件流，然后创建一个系统临时文件，把临时文件传递给FileInputStream使用
 */
public class CreatePath {

    private static String createTempFile(InputStream inputStream) throws IOException {
        File file = null;
        BufferedOutputStream outputStream = null;
        try {
            file = File.createTempFile("wtable", ".properties");
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (file != null) {
                file.deleteOnExit(); // 程序退出时删除临时文件
            }
        }
        return file.getCanonicalPath();
    }
}
