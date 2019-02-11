package com.xianyue.basictype;

/**
 * @author liuhongjun
 * @since 上午9:31 19-1-8
 */
public class LabelUtil {

    public static void main(String[] args) {
//        test_double_cycle();
        test_one_cycle();
    }

    /**
     * continue , break 都是针对的内层循环
     * <p>
     * 而 continue outer， breaker outer 都是针对外层的标签
     */
    public static void test_double_cycle() {
        int i = 0;
        outer:
        while (true) {
            System.out.println("Outer while loop");
            while (true) {
                i++;
                System.out.println("i = " + i);
                if (i == 1) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("break outer");
                    break outer;
                }
            }
        }
    }

    /**
     * 如果只有一层循环，那么break会直接停掉该循环
     */
    public static void test_one_cycle() {
        int i = 0;
        label:
        while (true) {
            System.out.println("i = " + ++i);
            if (1 == i) {
                System.out.println("continue");
                continue;
            }
            if (i == 3) {
                System.out.println("continue label");
                continue label;
            }
            if (i == 5) {
                System.out.println("break");
                break;
            }
            if (i == 7) {
                System.out.println("break label");
                break label;
            }
        }
    }

}
