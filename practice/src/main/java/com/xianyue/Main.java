package com.xianyue;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;

public class Main {

    private static transient ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:00"));
    public static void main(String[] args) throws Exception {
        double num1 = 50123.12E25;
        System.out.println(String.valueOf(num1));
        BigDecimal bd1 = new BigDecimal(num1);
        System.out.println(bd1.toPlainString());
        System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        HashMap<Long, Long> map = new HashMap<>();
        map.put(1L, 1L);
        map.put(2L, 2L);
        map.put(3L, 3L);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(Duration.ofSeconds(5))
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    throw new NullPointerException();
//                    return "10";
                }
            });

        cache.get("key1");
        cache.get("key1");
        cache.get("key2");
    }
}
