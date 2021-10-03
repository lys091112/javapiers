package com.xianyue.common.rxjava.impl;

import com.xianyue.common.rxjava.Observable;
import com.xianyue.common.rxjava.ObservableOnSubscribe;
import com.xianyue.common.rxjava.Observer;
import com.xianyue.common.rxjava.ObserverEmitter;

/**
 * @author liuhongjun
 * @since 2020-09-29
 */
public class ObserveCreate<T> extends Observable<T> {

    private ObservableOnSubscribe source;


    public ObserveCreate(ObservableOnSubscribe source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        StartEmitter emitter = new StartEmitter(observer);

        source.subscribe(emitter);
    }

    private class StartEmitter<T> implements ObserverEmitter<T> {

        private Observer<T> observer;

        public StartEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T value) {
            observer.onNext(value);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void onCancel() {
            // TODO
        }
    }
}
