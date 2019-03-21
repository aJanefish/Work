package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;

import com.zy.viewinject.ViewLayout;

import demo.okhttp.zy.com.okhttpdemo.R;


@ViewLayout(R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_retrofit);
    }

    @Override
    protected String getLog() {
        return "RetrofitActivity";
    }
}
