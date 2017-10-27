package com.xianyue.basictype;

import java.util.Random;

/**
 */
public class RandomPrac {

    public static void main(String[] args) {
        printHelloWorld();
    }


    /**
     * java
     */
    public static void printHelloWorld() {
        System.out.println((int)'`');
        System.out.println(randomString(-229985452) + " " + randomString(-147909649));
    }

    private static String randomString(int i){
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int k = ran.nextInt(27);
            System.out.print(k + " ");
            if (k == 0)
                break;

            sb.append((char)('`' + k));
        }
        System.out.println("");

        return sb.toString();
    }
}
