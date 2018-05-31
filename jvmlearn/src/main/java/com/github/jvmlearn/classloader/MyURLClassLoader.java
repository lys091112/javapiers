package com.github.jvmlearn.classloader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyURLClassLoader extends ClassLoader {

  private String dir;



  @Override
  public Class<?> findClass(String name) throws ClassNotFoundException {

    byte[] clazzData = loadClassData(name);
    if (null == clazzData) {
      throw new ClassNotFoundException();
    }
    return defineClass(name, clazzData, 0, clazzData.length);
  }

  private byte[] loadClassData(String name) {
    String filePath = dir + File.separatorChar + name.replace(".", File.separator) + ".class";

    try (FileInputStream fis = new FileInputStream(new File(filePath)); ByteArrayOutputStream os = new ByteArrayOutputStream()) {

      byte[] buf = new byte[1024];
      int len = 0;

      while ((len = fis.read(buf)) != -1) {
        os.write(buf, 0, len);
      }
      return os.toByteArray();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setDir(String dir) {
    this.dir = dir;
  }

}
