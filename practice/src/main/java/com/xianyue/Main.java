package com.xianyue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    }
}
