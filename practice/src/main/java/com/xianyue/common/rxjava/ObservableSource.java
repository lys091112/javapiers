package com.xianyue.common.rxjava;

/**
 * @author liuhongjun
 * @since 2020-09-29
 *
 * 被观察者
 */
public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);
}
