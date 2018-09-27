package com.butler.launcher.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.butler.launcher.R;
import com.butler.launcher.bean.AppInfo;
import com.suke.widget.SwitchButton;

import java.util.List;

public class LauncherSettingAdapter extends RecyclerView.Adapter<LauncherSettingAdapter.ViewHolder> {



    private List<AppInfo> appInfos;

    public LauncherSettingAdapter(List<AppInfo> appInfos) {
        this.appInfos = appInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_setting, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        AppInfo appInfo = appInfos.get(position);

        viewHolder.item_setting_app_name.setText(appInfo.getAppName());
        viewHolder.item_setting_icon.setImageBitmap(appInfo.getBitmap());
        viewHolder.item_settings_switchbutton.setChecked(true);
    }

    @Override
    public int getItemCount() {
        if(appInfos != null){
            return appInfos.size();
        }
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView item_setting_app_name;
        SwitchButton item_settings_switchbutton;
        ImageView item_setting_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_setting_app_name = itemView.findViewById(R.id.item_setting_app_name);
            item_settings_switchbutton = itemView.findViewById(R.id.item_settings_switchbutton);
            item_setting_icon = itemView.findViewById(R.id.item_setting_icon);
        }
    }
}
