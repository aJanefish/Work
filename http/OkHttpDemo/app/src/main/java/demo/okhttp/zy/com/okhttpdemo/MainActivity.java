package demo.okhttp.zy.com.okhttpdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.okhttp.zy.com.okhttpdemo.activity.BaseActivity;
import demo.okhttp.zy.com.okhttpdemo.adapter.MainAdapter;


public class MainActivity extends BaseActivity {


    private RecyclerView main_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDate();
        
    }

    @Override
    protected String getLog() {
        return "MainActivity1";
    }

    @SuppressLint("WrongConstant")
    private void initDate() {
        main_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        MainAdapter mainAdapter = new MainAdapter(Constant.getMainEvent(),getApplicationContext());
        main_recycler_view.setAdapter(mainAdapter);

    }

    private void initView() {
        main_recycler_view  = findViewById(R.id.main_recycler_view);
    }


    static class Inner1{

    }

    class Inner2{

    }

}
