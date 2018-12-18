package zy.walk.com.thewalkers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.imagesandanimations.event.DiyAnimationEvent;

public class DiyAnimationAdapter extends RecyclerView.Adapter<DiyAnimationAdapter.ViewHolder> {

    private List<DiyAnimationEvent> list;
    private DiyAnimationDegete diyAnimationDegete;

    public DiyAnimationAdapter(List<DiyAnimationEvent> list) {
        this.list = list;
    }


    public void setDiyAnimationDegete(DiyAnimationDegete diyAnimationDegete) {
        this.diyAnimationDegete = diyAnimationDegete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diy_adapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiyAnimationEvent diyAnimationEvent = list.get(position);
        holder.item_intent.setText(diyAnimationEvent.intent);
        holder.item_values.setText(diyAnimationEvent.values);
        holder.constrain_layout.setOnClickListener(new DiyAnimationAdapterListener(diyAnimationEvent));

    }

    public interface DiyAnimationDegete{
        void onClick(DiyAnimationEvent diyAnimationEvent);
    }

    class DiyAnimationAdapterListener implements View.OnClickListener{

        private DiyAnimationEvent diyAnimationEvent;

        public DiyAnimationAdapterListener(DiyAnimationEvent diyAnimationEvent) {
            this.diyAnimationEvent = diyAnimationEvent;
        }

        @Override
        public void onClick(View v) {
            if(diyAnimationDegete != null)
                diyAnimationDegete.onClick(diyAnimationEvent);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constrain_layout;
        private TextView item_intent;
        private TextView item_values;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            constrain_layout = itemView.findViewById(R.id.diy_adapter_item_constrain_layout);
            item_intent = itemView.findViewById(R.id.diy_adapter_item_intent);
            item_values = itemView.findViewById(R.id.diy_adapter_item_values);

        }
    }
}
