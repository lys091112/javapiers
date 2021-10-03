package com.xianyue.common;

import com.xianyue.common.rxjava.Observable;
import com.xianyue.common.rxjava.ObservableOnSubscribe;
import com.xianyue.common.rxjava.Observer;
import com.xianyue.common.rxjava.ObserverEmitter;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuhongjun
 * @since 2020-09-29
 */
public class RxStart {

  public static void main(String[] args) {
    //
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObserverEmitter<String> emitter) {
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
            emitter.onNext("4");
            emitter.onNext("5");
            emitter.onNext("6");
            emitter.onComplete();
        }
    });

    // 绑定观察者进行订阅
    observable
        .map((Function<String, Integer>) s -> 100 + Integer.parseInt(s))
        .filter((Predicate<Integer>)t -> t % 2 == 0)
        .subscribe(
        new Observer<Integer>() {
          @Override
          public void onSubscribe() {
            System.out.println("----------");
          }

          @Override
          public void onNext(Integer value) {
            System.out.println("receive value----" + value);
          }

          @Override
          public void onError(Throwable e) {}

          @Override
          public void onComplete() {
            System.out.println("receive complete");
          }
        });

  }
}
