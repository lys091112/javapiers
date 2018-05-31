package com.xianyue.basictype;

/**
 * 在try中无论如何final 都会被执行到， 如果final中return， 那么会覆盖try中的
 */
public class TryCatchCode {


    public static void main(String[] args) {
        TryCatchCode tryCatchCode = new TryCatchCode();
        System.out.println(tryCatchCode.try_and_final_return());

        System.out.println("----------------------");

        System.out.println(tryCatchCode.try_return_and_final_sout());

        System.out.println("----------------------");
        System.out.println(tryCatchCode.try_and_final_return_with_catch_throw());

        System.out.println("----------------------");
        System.out.println(tryCatchCode.try_return_and_final_sout_with_catch_throw());
    }


    public String try_and_final_return() {
        try {
            return "hello, i am try!";
        } finally {
            System.out.println("this is a final out");
            return "hello, i am final";
        }
    }

    public String try_return_and_final_sout() {
        try {
            return "hello, i am try!";
        } finally {
            System.out.println("try_return_and_final_sout: this is a final out");
        }
    }

    public String try_and_final_return_with_catch_throw() {
        try {
            int i = 4 / 0;
            return "hello , i am try";
        } catch (Exception e) {
            throw e;// 无法执行到，被final覆盖
        } finally {
            System.out.println("this is a final out");
            return "hello, i am final";
        }
    }

    public String try_return_and_final_sout_with_catch_throw() {
        try {
            String return_value;
            try {
                int i = 4 / 0;
                return_value = "hello , i am try";
            } catch (Exception e) {
                throw e;
            }
            // 不会被执行，catch已经阻止
            System.out.println("this is try sout");
            return return_value;
        } finally {
            System.out.println("this is a final out");
        }
    }

}
