package com.zy.myadapter;

public interface ItemViewDelegate<T> {

    int getLayoutId();

    boolean isShowing(T t,int position);

    void convert(BaseHolder holder, T t ,int position);

}
