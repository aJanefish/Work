package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.adapter.MainAdapter;
import zy.walk.com.thewalkers.diy.RunBall;
import zy.walk.com.thewalkers.utils.Constant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;



public class DiyViewActivity extends AppCompatActivity {


    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);
        initView();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        recycler_view = findViewById(R.id.activity_diy_view_recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        MainAdapter mainAdapter = new MainAdapter(Constant.getDiyViewEvent(),getApplicationContext());

        recycler_view.setAdapter(mainAdapter);

    }


}
