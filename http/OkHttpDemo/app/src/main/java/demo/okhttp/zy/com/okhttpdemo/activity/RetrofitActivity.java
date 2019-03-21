package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;
import android.view.View;

import com.zy.retrofit.RetrofitManager;
import com.zy.viewinject.ViewLayout;
import com.zy.viewinject.ViewMethod;
import com.zy.viewinject.ViewUtils;

import demo.okhttp.zy.com.okhttpdemo.R;


@ViewLayout(R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_retrofit);
        ViewUtils.register(this);
    }

    @Override
    protected String getLog() {
        return "RetrofitActivity";
    }

    @ViewMethod(getId = R.id.activity_retrofit_test1)
    private void test1(View view){
        RetrofitManager.request();
    }
}
