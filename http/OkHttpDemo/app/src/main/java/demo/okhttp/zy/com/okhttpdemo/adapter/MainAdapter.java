package demo.okhttp.zy.com.okhttpdemo.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import demo.okhttp.zy.com.okhttpdemo.R;
import demo.okhttp.zy.com.okhttpdemo.event.MainEvent;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private final List<MainEvent> list;
    private final Context context;
    private String TAG = "MainAdapter";

    public MainAdapter(List<MainEvent> list , Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainEvent mainEvent = list.get(position);
        holder.text_view_content.setText(mainEvent.content);
        holder.text_view_title.setText(mainEvent.title);
        holder.constraint_layout.setOnClickListener(new MainAdapterListener(mainEvent));

    }

    class MainAdapterListener implements View.OnClickListener {

        private MainEvent mainEvent;

        public MainAdapterListener(MainEvent mainEvent) {
            this.mainEvent = mainEvent;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,mainEvent.toString());
            Intent intent = new Intent();
            intent.setClassName(mainEvent.packageName,mainEvent.className);
            try {
                context.startActivity(intent);
            }catch (ActivityNotFoundException e){
                Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text_view_content;
        private final TextView text_view_title;
        private final ConstraintLayout constraint_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_view_content = itemView.findViewById(R.id.main_adapter_item_text_view_content);
            text_view_title = itemView.findViewById(R.id.main_adapter_item_text_view_title);
            constraint_layout = itemView.findViewById(R.id.main_adapter_item_constraint_layout);
        }
    }
}
