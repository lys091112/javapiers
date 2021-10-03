package com.xianyue.common.rxjava.impl;

import com.xianyue.common.rxjava.Observable;
import com.xianyue.common.rxjava.ObservableSource;
import com.xianyue.common.rxjava.Observer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuhongjun
 * @since 2020-09-29
 */
public class ObservableFilter<T> extends Observable<T> {

    private ObservableSource source;
    private Predicate<? super T> function;


    public ObservableFilter(ObservableSource source,Predicate<? super T> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        FilterObeserver<T> filterObeserver = new FilterObeserver<>(observer, function);
        source.subscribe(filterObeserver);
    }

    private class FilterObeserver<T> implements Observer<T>{

        private Observer<? super T> observer;
        private Predicate<? super T> function;

        public FilterObeserver(Observer<? super T> observer, Predicate<? super T> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T value) {
            if(function.test(value)) {
                observer.onNext(value);
            }
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
