package zy.walk.com.thewalkers.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {


    private static final String TAG = "DemoAdapter";
    List<String> list;

    public DemoAdapter(List<String> list) {
        Log.d(TAG, "Constructor:" + this);
        this.list = list;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:" + holder + " ,position:" + position);
        holder.item_list_title.setText(list.get(position));

    }


    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        Log.d(TAG, "getItemViewType position:" + position + " ,viewType:" + viewType);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_list_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "Constructor:" + this);
            item_list_title = itemView.findViewById(R.id.activity_adapter_item_list_title);
        }


        @Override
        public String toString() {
            return "{ViewHolder:" + this.hashCode() + "}";
        }
    }
}
