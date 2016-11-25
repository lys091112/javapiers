package com.xianyue.packageScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author XianYue
 * TODO
 * 类加载器 理解类记载器的流程，并对其进行分析
 * 添加logger，完善日志记录
 * 添加完整测试
 */
public class PackageScanner {

    private String packagePath; // 包名

    private Class  annotation;  // 要过滤的注解

    private String suffix;      // 要过滤的文件后缀

    private Logger logger = LoggerFactory.getLogger(PackageScanner.class);

    // private ClassLoader loader; //classLoader

    public PackageScanner(String packageName, Class annotation) {
        this(packageName);
        this.annotation = annotation;
    }

    public PackageScanner(String packageName) {
        this.packagePath = packageName;
    }

    public List<Class> scanClasses() {
        this.suffix = ".class"; //初始化为搜索class文件类型

        List<Class> classes = new ArrayList<>();
        try {
            List<String> files = loadResource();
            // 根据注解进行过滤
            if (files != null) {
                classes = filterAnnotations(files);
            }
        } catch (ClassNotFoundException | IOException e) {
            logger.error("scan class file error. exception: {}", suffix, e.getMessage());
        }
        return classes;
    }

    public List<String> scanFiles(String suffix) {
        this.suffix = suffix;
        if(!suffix.startsWith(".")) {
            this.suffix = "." + suffix;
        }

        List<String> res = new ArrayList<>();
        try {
            res = loadResource();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("scan file error. suffix is {}, exception: {}", suffix, e.getMessage());
        }
        return res;
    }

    private List<Class> filterAnnotations(List<String> files) throws ClassNotFoundException {
        List<Class> res = new ArrayList<>();
        for (String className : files) {
            res.add(Thread.currentThread().getContextClassLoader().loadClass(className));
        }
        return res.stream().filter(t -> annotation == null || t.getAnnotation(annotation) != null).collect(Collectors.toList());
    }

    /**
     * 加载符合条件的文件
     */
    private List<String> loadResource() throws IOException, ClassNotFoundException {
        List<String> list = new ArrayList<>();
        // convert to path
        String path = PathUtil.packageToPath(packagePath);
        // scanfile
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            ResourceType type = ResourceType.getResourceType(url);
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
            switch (type) {
                case FILE:
                    list.addAll(scanFiles(filePath, packagePath));
                    break;
                case JAR:
                    // scan jar
                    list.addAll(scanJars(url, packagePath));
                    break;
                case NONE:
                    logger.error("invaild type! type ={}", type.getType());
                    break;
            }
        }
        return list;
    }

    private List<String> scanJars(URL url, String packagePath) throws ClassNotFoundException {
        List<String> res = new ArrayList<>();
        try {
            JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (name.charAt(0) == '/') {
                    name = name.substring(1);
                }
                if (name.startsWith(packagePath) && name.endsWith(suffix)) {
                    String className = convertToClassName(name);
                    res.add(className);
                }
            }
        } catch (IOException e) {
            logger.error("scan jar file error. {}", e.getMessage());
        }
        return res;
    }

    /**
     * 遍历整个文件目录 Class clazz = Class.forName(t);
     * //如果使用class.forName的话，会默认初始化类的静态成员变量,因此此处使用的是loadClass
     */
    public List<String> scanFiles(String path, String packagePath) throws ClassNotFoundException {
        File sourceFile = new File(path);
        if (!sourceFile.exists() || !sourceFile.isDirectory()) {
            return new ArrayList<>();
        }

        File[] subFiles = sourceFile.listFiles(file -> file.isDirectory() || file.getName().endsWith(".class"));
        if (null == subFiles) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        for (File file : subFiles) {
            if (file.isDirectory()) {
                res.addAll(scanFiles(file.getAbsolutePath(), PathUtil.contactStr(packagePath, "/", file.getName())));
            } else {
                String className = convertToClassName(file.getName());
                if (className.contains("$")) { //对内部类不进行处理
                    continue;
                }
                String pathName = PathUtil.contactStr(packagePath, "/", className);
                res.add(PathUtil.pathToPackage(pathName));
                // res.add(Thread.currentThread().getContextClassLoader().loadClass(PathUtil.pathToPackage(pathName)));
            }
        }
        return res;
    }

    /**
     * 将文件名改为包名，分两步；1.去除文件后缀标识 2.把 "\"转化为"."
     */
    private String convertToClassName(String fileName) {
        String fileNameNoSuffix = PathUtil.trimSuffix(fileName);
        return PathUtil.pathToPackage(fileNameNoSuffix);
    }

    public static void main(String[] args) {
        PackageScanner scanner = new PackageScanner("com/xianyue");
        for (Class clazz : scanner.scanClasses()) {
            System.out.println("filename : " + clazz.getName());
        }
        for (String fileName: scanner.scanFiles("class")) {
            System.out.println("filename : " + fileName);
        }
    }

}
