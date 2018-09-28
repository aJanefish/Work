package com.butler.launcher.acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.support.v7.widget.RecyclerView;


import com.butler.launcher.R;
import com.butler.launcher.adapter.LauncherSettingAdapter;
import com.butler.launcher.model.LauncherModel;

public class SettingActivity extends AppCompatActivity {

    private RecyclerView setting_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();

    }

    private void initView() {
        setting_recyclerView = findViewById(R.id.setting_recyclerView);
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        //LauncherModel.

        setting_recyclerView.setLayoutManager(layoutManage);
        setting_recyclerView.setAdapter(new LauncherSettingAdapter(LauncherModel.appAllInfos));
    }

    public void settingBack(View view) {
        finish();
    }

    public void settingConfirm(View view) {
    }
}
