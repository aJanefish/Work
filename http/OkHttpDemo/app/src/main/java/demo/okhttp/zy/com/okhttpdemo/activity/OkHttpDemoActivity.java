package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import demo.okhttp.zy.com.okhttpdemo.R;

public class OkHttpDemoActivity extends AppCompatActivity {

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);
        initView();
    }

    private void initView() {
        recycler_view = findViewById(R.id.activity_ok_http_demo_recycler_view);
    }
}
