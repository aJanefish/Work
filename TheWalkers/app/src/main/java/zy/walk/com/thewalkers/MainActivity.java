package zy.walk.com.thewalkers;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.adapter.MainAdapter;
import zy.walk.com.thewalkers.event.MainEvent;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recycler_view_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private MainEvent createMainEvent(String title,String content,String packName,String className){
        return new MainEvent.Builder()
                .title(title)
                .content(content)
                .className(packName)
                .packageName(className)
                .bulde();
    }

    @SuppressLint("WrongConstant")
    private void initDate() {
        recycler_view_main.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        List<MainEvent> list = new ArrayList<>();
        list.add(createMainEvent("Other","用来做一些小测试之类页面","zy.walk.com.thewalkers","zy.walk.com.thewalkers.OtherActivity"));
        list.add(createMainEvent("Test","用来做一些小测试之类页面","zy.walk.com.thewalkers","zy.walk.com.thewalkers.TestActivity"));
        list.add(createMainEvent("Permission","了解android M 后动态申请权限的方法","zy.walk.com.thewalkers","zy.walk.com.thewalkers.PermissionActivity"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));
        list.add(createMainEvent("年后","ssss","zy.walk.com.thewalkers","zy.walk.com.thewalkers.MainActivity1"));

        MainAdapter mainAdapter = new MainAdapter(list,getApplicationContext());
        recycler_view_main.setAdapter(mainAdapter);

    }

    private void initView() {
        recycler_view_main = findViewById(R.id.recycler_view_main);
    }

}
