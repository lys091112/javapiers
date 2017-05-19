package com.xianyue.concurrent.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xianyue
 */
public class MapUse {


    public static void main(String[] args) {
        mapTest();
        computeIfPresentTest();
    }


    public static void mapTest() {
        Map<String, String> attrMap = new ConcurrentHashMap<>();

        attrMap.putIfAbsent("ContentType", "json");

        //get or is not exist then compute put
        String type = attrMap.computeIfAbsent("ContentType", t -> {
            return "xml";
        });
        System.out.println("type: " + type);

        String t2 = attrMap.computeIfPresent("ContentType", (k, v) -> v.toUpperCase());
        System.out.println("type2: " + t2);

        attrMap.entrySet().iterator();
    }

    public static void computeIfPresentTest() {
        Map<String, Integer> wordCounts = new LinkedHashMap<>();

        String s =
                "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                        "elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                        "labore et dolore magna dolor sit amet aliquyam erat sed diam";

        wordCounts.put("sed", 0);
        wordCounts.put("erat", 0);

        for (String t : s.split(" ")) {
            wordCounts.computeIfPresent(t, (k, v) -> v + 1);
        }
        System.out.println(wordCounts);
    }
}
