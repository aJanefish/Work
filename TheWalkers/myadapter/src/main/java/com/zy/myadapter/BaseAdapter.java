package com.zy.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder> {

    private List<T> list;
    //private Context mContext;

    private ItemViewDelegateManager itemViewDelegateManager;

    public BaseAdapter(Context mContext, List<T> list) {
        this.list = list;
        //this.mContext = mContext;

        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {

        if (useItemViewDelegateManager()){
            return itemViewDelegateManager.getItemViewType(list.get(position),position);
        }else {
            return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewDelegate<T>  delegate = itemViewDelegateManager.getDelegate(viewType);
        int layoutId = delegate.getLayoutId();
        BaseHolder baseHolder = BaseHolder.createViewHolder(parent,layoutId);
        return baseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        convert(holder, list.get(position), position);
    }

    private void convert(BaseHolder holder, T t ,int position) {
        itemViewDelegateManager.convert(holder, t ,position);
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;

        return list.size();
    }

    public ItemViewDelegateManager addDelegate(ItemViewDelegate<T> delegate){
        itemViewDelegateManager.addDelegate(delegate);
        return itemViewDelegateManager;
    }

    private boolean useItemViewDelegateManager(){
        return  itemViewDelegateManager.getItemViewDelegateSize() > 0;
    }
}
