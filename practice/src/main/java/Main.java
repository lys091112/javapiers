import java.util.HashMap;
import java.util.Map;

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
