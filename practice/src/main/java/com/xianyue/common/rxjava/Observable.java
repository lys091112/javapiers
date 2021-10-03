package com.xianyue.common.rxjava;

import com.xianyue.common.rxjava.impl.ObservableFilter;
import com.xianyue.common.rxjava.impl.ObservableMap;
import com.xianyue.common.rxjava.impl.ObserveCreate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuhongjun
 * @since 2020-09-29
 *
 * 被观察者
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribe(Observer<T> observer) {
       try{
           subscribeActual(observer);
       }catch (NullPointerException e) {
           throw e;
       }catch (Throwable a) {
           throw a;
       }
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

    public static <T> Observable<T> create(ObservableOnSubscribe<T> onSubscribe) {
        return new ObserveCreate<>(onSubscribe);
    }

    public <U> Observable<U> map( Function<? super T, ? extends U> function) {
        return new ObservableMap<>(this, function);
    }

    // 方法开头不能声明范型标识，不然类型推导将无法推导范型的类型
//    public <T> Observable<T> filter(Predicate<? super T> predicate) {
    public Observable<T> filter(Predicate<? super T> predicate) {
        return new ObservableFilter<>(this,predicate);
    }
}
