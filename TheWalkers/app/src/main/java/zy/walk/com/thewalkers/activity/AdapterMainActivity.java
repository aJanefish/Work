package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.imagesandanimations.event.MainEvent;
import zy.walk.com.thewalkers.utils.Constant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

public class AdapterMainActivity extends AppCompatActivity {

    private RecyclerView adapter_main_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_main);
        initView();
        initData();
    }

    private void initData() {

        adapter_main_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        CommonAdapter<MainEvent> commonAdapter = new CommonAdapter<MainEvent>(this, R.layout.main_adapter_item, Constant.getAdapterEvent()) {
            @Override
            protected void convert(ViewHolder holder, MainEvent mainEvent, int position) {
                holder.setText(R.id.main_adapter_item_text_view_title, mainEvent.title);
                holder.setText(R.id.main_adapter_item_text_view_content, mainEvent.content);
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                MainEvent mainEvent = Constant.getAdapterEvent().get(i);
                Intent intent = new Intent();
                intent.setClassName(mainEvent.packageName,mainEvent.className);
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(AdapterMainActivity.this,""+e,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });

        adapter_main_recycler_view.setAdapter(commonAdapter);
    }

    private void initView() {
        adapter_main_recycler_view = findViewById(R.id.activity_adapter_main_recycler_view);
    }
}
