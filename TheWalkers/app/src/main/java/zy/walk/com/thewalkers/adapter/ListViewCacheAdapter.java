package zy.walk.com.thewalkers.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import zy.walk.com.thewalkers.R;

public class ListViewCacheAdapter extends BaseAdapter {


    private static final String TAG = "ListViewCacheAdapter";
    private Context mContext;
    List<String> list;
    HashSet<View> hashSet;

    public ListViewCacheAdapter(Context context) {
        this.mContext = context;
        hashSet = new HashSet<>();
        list = new ArrayList<>();
        int tmp  = 52;
        for (int i = 0; i <= tmp; i++) {
            list.add(""+(char)('a'+i));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG,"getView:"+convertView+" - "+Log.getStackTraceString(new Throwable()));
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewHolder holder;

        if (convertView == null){
            //Log.d(TAG,position+" : (convertView == null)");
            convertView = inflater.inflate(R.layout.activity_list_view_cache_item, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);

//            holder.title.setText(list.get(position));
//            holder.des.setText(list.get(position)+" size:"+hashSet.size());
        }else {
            holder = (ViewHolder) convertView.getTag();
            hashSet.add(convertView);

        }


        holder.title.setText(list.get(position));
        holder.des.setText(list.get(position)+" size:"+hashSet.size());
        return convertView;
    }



    public static class ViewHolder {

        public TextView des;
        public TextView title;


        public ViewHolder(View convertView) {
            title = convertView.findViewById(R.id.activity_list_view_cache_item_title);
            des = convertView.findViewById(R.id.activity_list_view_cache_item_des);

        }
    }


}
