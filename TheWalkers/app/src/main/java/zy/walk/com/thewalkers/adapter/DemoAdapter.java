package zy.walk.com.thewalkers.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.imagesandanimations.event.DemoEvent;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {


    public static final String TAG = "DemoAdapter";
    List<DemoEvent> list;

    public DemoAdapter(List<DemoEvent> list) {
        Log.d(TAG, "Constructor:" + this);
        this.list = list;
    }

    public List<DemoEvent> getList() {
        return list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG, "onCreateViewHolder:" + viewHolder + " ,parent:" + parent.hashCode() + " ,viewType:" + viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:" + holder + " ,position:" + position);
        holder.item_list_title.setText(list.get(position).title);
        holder.constranint_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,position + ","+list.get(position) +" OnClick");
            }
        });

    }


    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        //Log.d(TAG, "getItemViewType position:" + position + " ,viewType:" + viewType);
        return viewType;
    }

    @Override
    public int getItemCount() {
        if (this.list == null) {
            return 0;
        }
        //Log.d(TAG,"getItemCount");
        return this.list.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        /**
         *
         * Called when a view created by this adapter has been recycled.
         * 当 ViewHolder 已经确认被回收，且要放进 RecyclerViewPool 中前，该方法会被回调
         * */
        Log.d(TAG,"onViewRecycled:"+holder);
        super.onViewRecycled(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG,"onAttachedToRecyclerView:"+recyclerView);
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d(TAG,"onDetachedFromRecyclerView:"+recyclerView);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        Log.d(TAG,"registerAdapterDataObserver:"+observer);
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        Log.d(TAG,"unregisterAdapterDataObserver:"+observer);
        super.unregisterAdapterDataObserver(observer);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_list_title;
        private final ConstraintLayout constranint_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "Constructor:" + this);
            item_list_title = itemView.findViewById(R.id.activity_adapter_item_list_title);
            constranint_layout = itemView.findViewById(R.id.activity_adapter_item_list_constranint_layout);
        }


        @Override
        public String toString() {
            return "{ViewHolder:" + this.hashCode() + "}";
        }
    }
}
