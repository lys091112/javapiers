package com.xianyue.basictype;

/**
 * @author Xianyue
 */
public class PracticeMain {

    private final static String str = "%d\t%s\t%s\t%s\t%d";

    public static void main(String[] args) {
       String end = String.format(str,1,null,null,null,5);
        String[] arrs = end.split("\t");
        System.out.println("lenght : " + arrs.length);
        System.out.println("arr[0] " + arrs[0]);
        System.out.println("arr[1] " + arrs[1]);
        System.out.println("arr[4] " + arrs[4]);
    }
}
