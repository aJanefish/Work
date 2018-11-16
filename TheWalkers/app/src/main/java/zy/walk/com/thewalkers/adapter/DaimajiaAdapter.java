package zy.walk.com.thewalkers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.event.DaimajiaEvent;

public class DaimajiaAdapter extends RecyclerView.Adapter<DaimajiaAdapter.ViewHolder> {

    private List<DaimajiaEvent> list;
    private DaimajiaAdapterDegele daimajiaAdapterDegele;

    public DaimajiaAdapter(List<DaimajiaEvent> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daimajia_adapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaimajiaEvent daimajiaEvent = list.get(position);
        holder.text_view_intent.setText(daimajiaEvent.intent);
        holder.relative_layout.setOnClickListener(new DaimajiaAdapterListener(daimajiaEvent));

    }

    public interface DaimajiaAdapterDegele{
        void onClick(DaimajiaEvent daimajiaEvent);
    }

    public void setDaimajiaAdapterDegele(DaimajiaAdapterDegele daimajiaAdapterDegele) {
        this.daimajiaAdapterDegele = daimajiaAdapterDegele;
    }

    private class DaimajiaAdapterListener implements View.OnClickListener{

        private DaimajiaEvent daimajiaEvent;

        public DaimajiaAdapterListener(DaimajiaEvent daimajiaEvent) {
            this.daimajiaEvent = daimajiaEvent;
        }

        @Override
        public void onClick(View v) {
            if(daimajiaAdapterDegele != null){
                daimajiaAdapterDegele.onClick(daimajiaEvent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RelativeLayout relative_layout;
        private final TextView text_view_intent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relative_layout = itemView.findViewById(R.id.daimajia_adapter_item_relative_layout);
            text_view_intent = itemView.findViewById(R.id.daimajia_adapter_item_text_view_intent);
        }
    }
}
