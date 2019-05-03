package com.zy.glidedemo.myadapter;

import androidx.collection.SparseArrayCompat;

public class ItemViewDelegateManager<T> {

    SparseArrayCompat<ItemViewDelegate<T>> sparseArrayCompat = new SparseArrayCompat<>();

    public ItemViewDelegateManager() {
        sparseArrayCompat.clear();
    }


    public int getItemViewDelegateSize(){
        return sparseArrayCompat.size();
    }

    public void addDelegate(ItemViewDelegate<T> delegate){
        int viewType = getItemViewDelegateSize();
        if(delegate != null){
            sparseArrayCompat.put(viewType,delegate);
        }
    }

    public ItemViewDelegate<T> getDelegate(int viewType) {
        return sparseArrayCompat.get(viewType);
    }


    public <T> int getItemViewType(T t, int position) {
        int delegates = sparseArrayCompat.size();
        for(int i = 0 ; i < delegates; i++){
            ItemViewDelegate<T> delegate = (ItemViewDelegate<T>) sparseArrayCompat.valueAt(i);
            if(delegate.isShowing(t,position)){
                return sparseArrayCompat.keyAt(i);
            }
        }

        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public <T> void convert(BaseHolder holder, T t, int position) {

        int delegates = sparseArrayCompat.size();
        for(int i = 0 ; i < delegates; i++){
            ItemViewDelegate<T> delegate = (ItemViewDelegate<T>) sparseArrayCompat.valueAt(i);
            if(delegate.isShowing(t,position)){
                delegate.convert(holder,t,position);
                return;
            }
        }

        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");

    }
}
