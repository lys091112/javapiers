package com.xianyue.common.stater;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-14
 */
public class ResultMode {

    private int mode;

    public ResultMode(int mode) {
        this.mode = mode;
    }

    public int mask() {
        return 1 << mode;
    }

    public int steps() {
        return mode;
    }

    public static final ResultMode RESULT_MODE2 = new ResultMode(2);
    public static final ResultMode RESULT_MODE3 = new ResultMode(3);

}
