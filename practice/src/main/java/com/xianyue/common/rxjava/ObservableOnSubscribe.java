package com.xianyue.common.rxjava;

/**
 * @author liuhongjun
 * @since 2020-09-29
 *
 * 被观察者
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(ObserverEmitter<T> emitter);
}
