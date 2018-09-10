package com.butler.launcher.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.butler.launcher.R;
import com.butler.launcher.bean.AppInfo;

import java.util.List;

public class LauncherAdapter extends RecyclerView.Adapter<LauncherAdapter.ViewHolder> {
	
	private static final String TAG = "LauncherAdapter";
	private List<AppInfo> list;
	private Context context;
	
	public LauncherAdapter(Context context, List<AppInfo> list) {
		this.list = list;
		this.context = context;
	}
	
	
	/**
	 * 插入一条数据
	 *
	 * @param index 下标
	 * @param appInfo     数据
	 */
	public void addItem(int index, AppInfo appInfo) {
		list.add(index, appInfo);
		notifyItemInserted(index);
	}
	
	/**
	 * 删除一条数据
	 *
	 * @param index 下标
	 */
	public void deleteItem(int index) {
		list.remove(index);
		notifyItemRemoved(index);
	}
	
	
	@Override
	public LauncherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_launcher, parent, false);
		LauncherAdapter.ViewHolder viewHolder = new LauncherAdapter.ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(LauncherAdapter.ViewHolder holder, int position) {
//		holder.mText.setText(list.get(position));
//		int adapterPosition = holder.getAdapterPosition();
		AppInfo appInfo = list.get(position);
		holder.appname_launcher_item.setText(appInfo.getAppName());
		
		//holder.icon_launcher_item.setImageDrawable(appInfo.getIcon());
		holder.icon_launcher_item.setImageBitmap(appInfo.getBitmap());
		
		holder.item_relativelayout.setOnClickListener(new OnButletLauncherClickListener(appInfo,position));
		holder.item_relativelayout.setOnLongClickListener(new OnButletLauncherLongClickListener(appInfo,position));
		
	}
	
	@Override
	public int getItemCount() {
		return list.size();
	}
	
	class ViewHolder extends RecyclerView.ViewHolder {
		TextView appname_launcher_item;
		ImageView icon_launcher_item;
		RelativeLayout item_relativelayout;
		
		ViewHolder(View itemView) {
			super(itemView);
			appname_launcher_item = itemView.findViewById(R.id.appname_launcher_item);
			icon_launcher_item = itemView.findViewById(R.id.icon_launcher_item);
			item_relativelayout = itemView.findViewById(R.id.item_relativelayout);
		}
	}
	
	
	private class  OnButletLauncherClickListener implements View.OnClickListener {
		
		private AppInfo appInfo;
		private int position;
		
		public OnButletLauncherClickListener(AppInfo appInfo, int position) {
			this.appInfo = appInfo;
			this.position = position;
		}
		
		
		@Override
		public void onClick(View view) {
			Log.d(TAG,"onClick "+position+" ,["+appInfo.getPackageName()+","+appInfo.getClassName()+"]");
			

			
			Intent intent = context.getPackageManager().getLaunchIntentForPackage(appInfo.getPackageName());
			context.startActivity(intent);
			
		}
	}
	
	
	private class  OnButletLauncherLongClickListener implements View.OnLongClickListener {
		
		private AppInfo appInfo;
		private int position;
		
		public OnButletLauncherLongClickListener(AppInfo appInfo, int position) {
			this.appInfo = appInfo;
			this.position = position;
		}
		
		@Override
		public boolean onLongClick(View view) {
			Log.d(TAG,"onLongClick "+position+" , "+appInfo+"");
			return true;
		}
	}
	
	
	
	
}