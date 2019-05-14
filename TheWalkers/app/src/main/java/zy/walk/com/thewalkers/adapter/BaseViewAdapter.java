package zy.walk.com.thewalkers.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.event.BaseViewEvent;

public class BaseViewAdapter extends BaseAdapter {


    private final List<BaseViewEvent> list;
    private final Context mContext;
    private BaseViewAdapterListener baseViewAdapterListener;

    public interface BaseViewAdapterListener {
        void onEvent(BaseViewEvent baseViewEvent);
    }

    public BaseViewAdapter(List<BaseViewEvent> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    public void setBaseViewAdapterListener(BaseViewAdapterListener baseViewAdapterListener) {
        this.baseViewAdapterListener = baseViewAdapterListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.activity_view_base_item, null);
            baseViewHolder = new BaseViewHolder(convertView);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }
        baseViewHolder.title.setText(list.get(position).getName());
        baseViewHolder.des.setText(list.get(position).getDes());
        baseViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("BaseViewAdapter","getView:"+position+" - "+list.get(position));
                if (baseViewAdapterListener != null) {
                    baseViewAdapterListener.onEvent(list.get(position));
                }
            }
        });
        return convertView;
    }


    class BaseViewHolder {
        public final Button title;
        private final TextView des;

        private BaseViewHolder(View convertView) {
            title = convertView.findViewById(R.id.activity_view_base_item_title);
            des = convertView.findViewById(R.id.activity_view_base_item_des);
        }
    }


}
