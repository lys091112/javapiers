package com.xianyue.basictype.jvm.generic.sinker;

import com.xianyue.basictype.jvm.utils.CopyTypeResolver;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 下午1:40 18-8-31
 */
public class SinkerCreator {

//    public static List<Sinker<Fruit>> createSinker() {
//        List<Sinker<Fruit>> sinkers = new ArrayList<>();
//        sinkers.add(new AppleSinker());
//        sinkers.add(new OrangeSinker());
//        return sinkers;
//    }
    public static List<Sinker> createSinker() {
        List<Sinker> sinkers = new ArrayList<>();
        sinkers.add(new AppleSinker());
        sinkers.add(new OrangeSinker());
        return sinkers;
    }


    public static void main(String[] args) {
        createSinker().forEach(sinker -> {
            System.out.println(CopyTypeResolver.introspector(sinker.getClass()));
            sinker.sink(null);
        });
    }
}
