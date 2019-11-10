package com.xianyue.common.stater.record;

import com.xianyue.common.stater.StateType;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-13
 */
public abstract class AbstractStateType<TYPE extends Enum<TYPE>> implements StateType {

    private TYPE type;

    public AbstractStateType(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

}
