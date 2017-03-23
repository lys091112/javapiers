package com.xianyue.basictype.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xianyue
 */
public class UrlRegex {

    public static Optional<String> getHost(String url) {
        Pattern pattern = Pattern.compile("^((http|https)://)?((\\w)+\\.)+\\w+");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            return Optional.of(matcher.group());
        }
        return Optional.empty();
    }

    /**
     *
     * @param url
     * @return
     */
    public static Optional<String> getHost2(String url){
        URL urlStr = null;
        try {
            urlStr = new URL(url);
            System.out.println("xxx "+ url + " host " + urlStr.getHost());
            return Optional.of(urlStr.getHost());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private static boolean ping(String host) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        InputStream is = null;
        try {
            process = runtime.exec("ping " + host);
            is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().anyMatch(t -> t.toUpperCase().contains("TTL"));
        } catch (IOException e) {
        }
        return false;
    }

    private static void urlConvert() {
        String app2SysPatternStr = "/api/v3.*/applications/\\d.*";
        String url = "/api/v3/alert/applications/1/rules/ONEAPM_ALERT_AI_9_1487756700972_webTransaction_1_1";
        Pattern pattern = Pattern.compile(app2SysPatternStr);
        if (Pattern.compile(app2SysPatternStr).matcher(url).matches()) {
            url = url.replace("applications", "systems");
        }
        System.out.println("after urlConvert url is :" + url);
    }



    public static void main(String[] args) {
        String url = "http://www.baidu.com/index.html";
        Optional<String> result = getHost(url);
        System.out.println("host is " + result.get());
        System.out.println("host is " + getHost2("http://localhost:8080/index.html").get());
        System.out.println("host is " + getHost2("http://10.128.9.23:8090/index.html").get());
        System.out.println("host is not exist. " + getHost2("http:localhost/index.html").get());
        boolean res = ping("localhost");
        if(res) {
            System.out.println("ping true");
        }
        urlConvert();
    }
}
