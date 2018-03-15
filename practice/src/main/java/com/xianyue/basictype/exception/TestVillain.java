package com.xianyue.basictype.exception;

/**
 * stackOverFlow: https://stackoverflow.com/questions/13883166/uncatchable-chucknorrisexception
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
