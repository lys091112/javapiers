package com.xianyue.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xianyue
 */
public class GuavaMain {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("liliy");
        String res = Joiner.on(" and ").join(list) + " ";
        System.out.println(res);


        //ImmutableMap不变的只是对象，但是引用对象中内容还是可以变换的，跟immutable.js所实现的还是有区别
        String key  = "yue";
        Map<String, List<String>> map = ImmutableMap.of(key,list);
        list.add("dai");
        list.add("wen");

        System.out.println(list);
        System.out.println(map);

        Long a = new Long(3);
        Long b = new Long(3);
        System.out.println(a == b);
        System.out.println(a.equals(b));

        List<String> list2 = new ArrayList<>();
        boolean flag = list2.contains("hell0");
        System.out.println(flag);

        Pattern innerPattern = Pattern.compile("(InnerCall/){0,1}\\d+#\\d+/[^/]*/([^/]*)/(\\d+)");
        String metricName  = "InnerCall/2#3/oneapm/hell/43";
        Matcher matcher = innerPattern.matcher(metricName);
        if(matcher.matches()) {
            System.out.println("first " + matcher.group(1));
            System.out.println("first " + matcher.group(2));
            System.out.println("first " + matcher.group(3));
        }

        String metricName2  = "2#3/oneapm/hell/43";
        matcher = innerPattern.matcher(metricName2);
        if(matcher.matches()) {
            System.out.println("second " + matcher.group(1));
            System.out.println("second " + matcher.group(2));
            System.out.println("second " + matcher.group(3));
        }

        Pattern pattern = Pattern.compile("ExternalCall/[^/]*/([^/]*)/((?!all).)*");
        String external = "ExternalCall/HTTP/Mock/login_service";
        Matcher externalMatcher = pattern.matcher(external);
        if(externalMatcher.matches()) {
            System.out.println("external first");
        }

        String external2 = "ExternalCall/HTTP/Mock/all";
        Matcher externalMatcher2 = pattern.matcher(external2);
        if(externalMatcher2.matches()) {
            System.out.println("external second");
        }

//        int index = external.indexOf("/");
        int index = getCharacterPosition(external,2);
        System.out.println("index " +  index);
        System.out.println(external.substring(0, index));


        System.out.println(Joiner.on("").join(Arrays.asList("InnerCall","/","HTTP/all")));
    }

    public static int getCharacterPosition(String string, int n){
        //这里是获取"#"符号的位置
        Matcher slashMatcher = Pattern.compile("/").matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            //当"#"符号第二次出现的位置
            if(++mIdx == n){
                break;
            }
        }
        return slashMatcher.start();
    }
}
