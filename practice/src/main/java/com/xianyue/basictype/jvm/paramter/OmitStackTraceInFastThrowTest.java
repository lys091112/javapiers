package com.xianyue.basictype.jvm.paramter;

/**
 * @since 下午2:11 18-11-14
 * <p>
 * <p>
 * -XX:-OmitStackTraceInFastThrow  不配置会造成异常信息丢失
 */
public class OmitStackTraceInFastThrowTest {

    public static void main(String[] args) {
        int i = 0;
        String x = null;
        while (i < 100000000) {
            try {
                System.out.println("当前执行次数为：" + i);
                getNPE(x);
            } catch (Exception e) {
                int lth = e.getStackTrace().length;
                System.out.println("length：" + lth);
                e.printStackTrace();
                if (lth == 0) {
                    // 执行n次后异常会丢失
                    return;
                }
            }
            i++;
        }


    }

    private static void getNPE(String x) {
        System.out.println("当前字母为：" + x.toString());
    }
}
