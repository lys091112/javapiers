package com.xianyue.basictype.exception;

/**
 * stackOverFlow: https://stackoverflow.com/questions/13883166/uncatchable-chucknorrisexception
 * 验证一个未捕获的异常是否会导致程序崩溃。
 */
public class TestVillain {

    public static void main(String[] args) {
        try {
            throw new ChuckNorrisException();
        } catch (Throwable t) {
            System.out.println("Gotcha!");
        } finally {
            System.out.println("The end.");
        }
    }
}
