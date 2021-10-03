package com.xianyue.common.rxjava;

/**
 * @author liuhongjun
 * @since 2020-09-29
 */
public interface ObserverEmitter<T> {

    void onSubscribe();

    void onNext(T value);

    void onError(Throwable e);

    void onComplete();

    void onCancel();
}
