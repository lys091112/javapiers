package com.xianyue.common.stater.record.resultstate;

import com.xianyue.common.stater.inform.InformResultEvent;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-13
 */
public abstract class InformResultState extends ResultState<InformResultEvent> {

    public InformResultState(InformResultEvent informResultEvent) {
        super(informResultEvent);
    }

    @Override
    public int mask() {
        return 0X11;
    }

    @Override
    public int steps() {
        return 2;
    }

    @Override
    public int getFlag() {
        return getType().getFlag();
    }
}
