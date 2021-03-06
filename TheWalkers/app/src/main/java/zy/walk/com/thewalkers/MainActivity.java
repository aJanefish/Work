package zy.walk.com.thewalkers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.activity.BaseActivity;
import zy.walk.com.thewalkers.adapter.MainAdapter;
import zy.walk.com.thewalkers.utils.Constant;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recycler_view_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindowManager();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initDate() {
        recycler_view_main.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        MainAdapter mainAdapter = new MainAdapter(Constant.getMainEvent(),getApplicationContext());

        recycler_view_main.setAdapter(mainAdapter);


    }

    @Override
    protected String getLog() {
        return TAG;
    }

    @Override
    public void initView() {
        recycler_view_main = findViewById(R.id.recycler_view_main);
    }

}
