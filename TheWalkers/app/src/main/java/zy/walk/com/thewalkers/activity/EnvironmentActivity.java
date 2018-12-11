package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class EnvironmentActivity extends AppCompatActivity {

    private RecyclerView environment_recycler_view;
    private ArrayList<EnvironmentEvent> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        initView();
        initDate();
    }

    private void initDate() {

        mDatas = new ArrayList<>();
        initEnvironmentEvent();

        CommonAdapter<EnvironmentEvent> mAdapter = new CommonAdapter<EnvironmentEvent>(this, R.layout.activity_environment_item_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, EnvironmentEvent environmentEvent, int position) {
                holder.setText(R.id.activity_environment_item_list_title, environmentEvent.title);
                holder.setText(R.id.activity_environment_item_list_values, environmentEvent.values);
            }
        };

        environment_recycler_view.setAdapter(mAdapter);
    }

    private void initEnvironmentEvent() {
        mDatas.add(new EnvironmentEvent("Environment.getExternalStorageState()",Environment.getExternalStorageState()));

        mDatas.add(new EnvironmentEvent("Environment.getDataDirectory()",""+Environment.getDataDirectory()));
        mDatas.add(new EnvironmentEvent("Environment.getDownloadCacheDirectory()",""+Environment.getDownloadCacheDirectory()));
        mDatas.add(new EnvironmentEvent("Environment.getExternalStorageDirectory()",""+Environment.getExternalStorageDirectory()));
        mDatas.add(new EnvironmentEvent("Environment.getRootDirectory()",""+Environment.getRootDirectory()));

        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_ALARMS",Environment.DIRECTORY_ALARMS));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_DCIM",Environment.DIRECTORY_DCIM));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_DOCUMENTS",Environment.DIRECTORY_DOCUMENTS));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_DOWNLOADS",Environment.DIRECTORY_DOWNLOADS));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_MOVIES",Environment.DIRECTORY_MOVIES));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_MUSIC",Environment.DIRECTORY_MUSIC));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_NOTIFICATIONS",Environment.DIRECTORY_NOTIFICATIONS));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_PICTURES",Environment.DIRECTORY_PICTURES));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_PODCASTS",Environment.DIRECTORY_PODCASTS));
        mDatas.add(new EnvironmentEvent("Environment.DIRECTORY_RINGTONES",Environment.DIRECTORY_RINGTONES));

        mDatas.add(new EnvironmentEvent("",""));
        mDatas.add(new EnvironmentEvent("",""));
        mDatas.add(new EnvironmentEvent("",""));
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        environment_recycler_view = findViewById(R.id.activity_environment_recycler_view);
        environment_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    class EnvironmentEvent{

        public String title;
        public String values;

        public EnvironmentEvent(String title, String values) {
            this.title = title;
            this.values = values;
        }
    }

}
