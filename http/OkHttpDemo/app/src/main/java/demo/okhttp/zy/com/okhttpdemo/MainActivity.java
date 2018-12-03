package demo.okhttp.zy.com.okhttpdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import demo.okhttp.zy.com.okhttpdemo.adapter.MainAdapter;


public class MainActivity extends AppCompatActivity {


    private RecyclerView main_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDate();
        
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

}
