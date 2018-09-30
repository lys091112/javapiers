package com.xianyue.basictype.generic.sinker;

import com.xianyue.basictype.generic.Fruit;

/**
 * @since 下午1:37 18-8-31
 */
public abstract class Sinker<T> {

    public abstract void sink(T fruit);

}
