package com.github.jvmlearn.classinnitial;

/**
 * 主要是当类出现同名fileds
 * 参考连接： <a href=https://blog.jooq.org/2017/07/20/a-curious-java-language-feature-and-how-it-produced-a-subtle-bug/>
 */
class SameFieldB {
    private String f = "SameFiled.B";
//    String f = "SameFiled.B"; // 01
}

class SameFieldC {
    String f = "SameFiled.C"; //02
    String oldF = "SameFiled.C";

    class SameFileD extends SameFieldB {
        void m() {
            System.out.println(f);
        }
    }
}

public class SameFieldMain {
    public static void main(String[] args) {
        new SameFieldC().new SameFileD().m();
    }
}
