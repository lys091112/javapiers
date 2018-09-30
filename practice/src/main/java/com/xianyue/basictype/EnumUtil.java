package com.xianyue.basictype;

/**
 * Enum 的初始化，当第一次使用时，会初始化所有的成员
 */
public enum EnumUtil {
    BLACK(new Con("black")), RED(new Con("red")), WHITE(new Con("white"));

    private Con con;

    EnumUtil(Con con) {
        this.con = con;
    }

    public static void main(String[] args) {
        EnumUtil red = EnumUtil.RED;
    }

    public static class Con {

        private String color;

        public Con(String color) {
            this.color = color;
            System.out.println("----color= " + color);
        }
    }
}
