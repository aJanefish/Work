package zy.walk.com.thewalkers.adapter;


import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;


import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.imagesandanimations.event.ListViewDemoEvent;


public class ListViewDemoAdapter extends BaseAdapter {

    private final Context mContext;
    private final Handler handler;
    private List<ListViewDemoEvent> listViewDemoEvents;
    private String TAG = "ListViewDemoAdapter";
    private HashSet<Integer> hashSet = new HashSet();

    public ListViewDemoAdapter(List<ListViewDemoEvent> listViewDemoEvents, Context mContext) {
        this.listViewDemoEvents = listViewDemoEvents;
        this.mContext = mContext;
        this.handler = new Handler();
    }

    @Override
    public int getCount() {
        if (listViewDemoEvents == null) {
            return 0;
        }
        return listViewDemoEvents.size();
    }

    @Override
    public Object getItem(int position) {
        if (listViewDemoEvents == null) {
            return null;
        }
        return listViewDemoEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        //ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_list_view_demo_list_view_item_root, null);
        final ViewHolder holder;
        ListViewDemoEvent listViewDemoEvent = listViewDemoEvents.get(position);

        Log.d(TAG, "convertView:" + convertView + ""+Log.getStackTraceString(new Throwable()));

        if (convertView == null) {
            //Log.d(TAG,position+" : (convertView == null)");
            convertView = inflater.inflate(R.layout.activity_list_view_demo_list_view_item, null);
            holder = new ViewHolder();

            holder.textView = convertView.findViewById(R.id.activity_list_view_demo_list_view_item_text_view);
            holder.textView_des = convertView.findViewById(R.id.activity_list_view_demo_list_view_item_text_view_des);

            holder.imageView = convertView.findViewById(R.id.activity_list_view_demo_list_view_item_image_view);


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
            //Log.d(TAG,position+" : "+holder);
        }
        hashSet.add(holder.hashCode());
        Log.d(TAG, "position:" + position + " : " + Thread.currentThread() + " : " + hashSet.size() + " : " + holder.imageView.hashCode());


        if (position == 0) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(position == (int)holder.imageView.getTag()){
                        holder.imageView.setImageResource(R.drawable.bitmpa2);
                    }

                    holder.textView_des.setText("   position:" + position + " : " + holder.imageView.getTag());


                }
            }, 2000);
        }


        holder.imageView.setImageResource(listViewDemoEvent.id);

        holder.imageView.setTag(position);

        holder.textView.setText(position + " : " + listViewDemoEvent.name);
        holder.textView.setTag(position);


        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView_des;
    }
}
