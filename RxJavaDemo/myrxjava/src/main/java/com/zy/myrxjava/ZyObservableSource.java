package com.zy.myrxjava;

import com.zy.myrxjava.annotations.NonNull;

public interface ZyObservableSource<T> {

    void subscribe(@NonNull ZyObserver<? super T> observer);
}
