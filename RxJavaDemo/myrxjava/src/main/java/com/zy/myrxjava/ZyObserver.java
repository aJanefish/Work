package com.zy.myrxjava;

import com.zy.myrxjava.annotations.NonNull;

public interface ZyObserver<T> {
    void onSubscribe(@NonNull ZyDisposable d);

    void onNext(@NonNull T t);

    void onError(@NonNull Throwable e);

    void onComplete();
}
