package com.xianyue.common.rxjava.impl;

import com.xianyue.common.rxjava.Observable;
import com.xianyue.common.rxjava.ObservableSource;
import com.xianyue.common.rxjava.Observer;
import java.util.function.Function;

/**
 * @author liuhongjun
 * @since 2020-09-29
 */
public class ObservableMap<T,U> extends Observable<U> {

    private ObservableSource source;
    private Function<? super T,? extends U> function;

    public ObservableMap(ObservableSource source,Function<? super T,? extends U> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<? super U> observer) {
        MapObeserver<T,U> mapObeserver = new MapObeserver<>(observer, function);
        source.subscribe(mapObeserver);
    }

    private class MapObeserver<T,U> implements Observer<T>{

        private Observer<? super U> observer;
        private Function<? super T,? extends U> function;

        public MapObeserver(Observer<? super U> observer, Function<? super T,? extends U> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T value) {
            observer.onNext(function.apply(value));
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }

}
