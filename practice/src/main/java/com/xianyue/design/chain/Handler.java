package com.xianyue.design.chain;

import lombok.Setter;

/**
 * Created by langle on 2018/1/21.
 */
public abstract class Handler {

    @Setter
    protected Handler next;

    public abstract void handle(HandleParam param);

    public abstract boolean isFit(HandleParam param);

}
