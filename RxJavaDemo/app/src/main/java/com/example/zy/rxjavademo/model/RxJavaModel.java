package com.example.zy.rxjavademo.model;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCreate;

public class RxJavaModel {

    private static final String TAG = "RxJavaModel";

    public static void simple() {

        ObservableCreate<Integer> observable = (ObservableCreate<Integer>) Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者(Observable) & 定义需发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                showLog("subscribe - emitter:" + emitter);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                showLog("onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                showLog("onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                showLog("onError:" + e);
            }

            @Override
            public void onComplete() {
                showLog("onComplete");
            }
        };


        showLog("observable:" + observable.toString());
        showLog("observer:" + observer.toString());
        observable.subscribe(observer);
    }

    private static void showLog(String data) {
        System.out.println(Thread.currentThread() + " - " + TAG + " --- " + data);
    }
}
