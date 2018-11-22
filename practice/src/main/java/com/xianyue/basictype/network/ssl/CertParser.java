package com.xianyue.basictype.network.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CertParser {


    /***
     * 读取*.cer公钥证书文件， 获取公钥证书信息
     */
    public void parser(String filePath) {

        try (InputStream inStream = new FileInputStream(new File(filePath))) {
            // 创建X509工厂类
            CertificateFactory cf = CertificateFactory.getInstance("X509");

            // 创建证书对象
            X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);

            String info;
            info = String.valueOf(oCert.getVersion());
            System.out.println("证书版本:" + info);

            info = oCert.getSerialNumber().toString(16);
            System.out.println("证书序列号:" + info);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date validDate = oCert.getNotBefore();
            info = dateFormat.format(validDate);
            System.out.println("证书生效日期:" + info);

            Date invalidDate = oCert.getNotAfter();
            info = dateFormat.format(invalidDate);
            System.out.println("证书失效日期:" + info);
            // 获得证书主体信息
            info = oCert.getSubjectDN().getName();
            System.out.println("证书拥有者:" + info);
            // 获得证书颁发者信息
            info = oCert.getIssuerDN().getName();
            System.out.println("证书颁发者:" + info);
            // 获得证书签名算法名称
            info = oCert.getSigAlgName();
            System.out.println("证书签名算法:" + info);
        } catch (Exception e) {
            System.out.println("解析证书出错！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CertParser().parser("/home/liuhongjun/xianyue/work/keystore.cer");
    }
}
