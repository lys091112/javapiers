package com.xianyue.basictype.generic.sinker;

import com.xianyue.basictype.generic.Fruit;
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
        createSinker().forEach(sinker -> sinker.sink(null));
    }
}
