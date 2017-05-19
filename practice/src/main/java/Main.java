import org.apache.commons.collections.map.HashedMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Xianyue
 */
public class Main {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(112, "hello");
        map.put(159, "hello");//null
        map.put(177, "hello");
        map.put(189, "hello");//null
        map.put(191, "hellosdfsdfoij");
        map.put(156, "hello");//null
        map.put(173, "hello");
        map.put(222, "hello");
        for (Map.Entry<Integer, String> k : map.entrySet()) {
            System.out.println(k.getKey());
        }
        switchTest("3");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("hello", 3L);
        System.out.println((long) map1.get("hello"));
        long k = (long) map1.get("hello");
        System.out.println(k + 1);
        if (null == map1.get("xx")) {
            System.out.println("xxxxxxxxxxx");
        }

        Long k2 = (Long) map1.get("xxx");
        if (null == k2) {
            System.out.println("scsdfi");
        }

        Map<Long, Boolean> map3 = new ConcurrentHashMap<>();
        map3.put(4L, false);

        boolean k3 = map3.getOrDefault(5L, getB(3L));
        System.out.println("k3 ---" + k3);

        boolean k4 = map3.getOrDefault(4L, getB(3L));
        System.out.println("k4 ---" + k4);
    }

    public static boolean getB(long systemId) {
        System.out.println("i am coming");
        return true;
    }

    // 在非并行的lambda处理中，所有的处理过程都是在一个线程中执行
    public static void lambdaExecThread() {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            str.add("hello" + i);
        }

        Set<Long> threadIds = new HashSet<>();
        str.forEach(s -> {
            threadIds.add(Thread.currentThread().getId());
//            System.out.println(s + ", Thread ---> " + Thread.currentThread().getId());
        });

        Set<Long> threadId2s = new HashSet<>();
        str.stream().map(t -> t + "ha").collect(Collectors.toList()).forEach(s -> {
            threadId2s.add(Thread.currentThread().getId());
//            System.out.println(s + ", Thread " +
//                    "---> " + Thread.currentThread().getId())
        });

        Set<Long> threadId3s = new HashSet<>();
        str.parallelStream().map(t -> {
            threadId3s.add(Thread.currentThread().getId());
            return t + "ha" + Thread.currentThread().getId();
        }).collect(Collectors.toList()).forEach(s -> {
//            System.out.println(s + ", " +
//                    "Thread " +
//                    "---> " + Thread.currentThread().getId());
        });
        System.out.println(threadIds);
        System.out.println(threadId2s);
        System.out.println(threadId3s);
    }

    public static void switchTest(String k) {
        final String i = "1";
        final String j = "2";
        switch (k) {
            case i:
                System.out.println(i);
                break;
            case j:
                System.out.println(j);
                break;
            default:
                System.out.println("hello");
        }
    }
}
