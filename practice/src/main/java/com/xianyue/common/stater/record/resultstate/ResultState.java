package com.xianyue.common.stater.record.resultstate;

import com.xianyue.common.stater.record.AbstractStateType;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-13
 */
public abstract class ResultState<TYPE extends Enum<TYPE>> extends AbstractStateType<TYPE> {

    public ResultState(TYPE type) {
        super(type);
    }

    public abstract int getFlag();

    public abstract int mask();

    public abstract int steps();


}
