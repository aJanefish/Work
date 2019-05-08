package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.retrofit.RetrofitManager;
import com.zy.viewinject.ViewField;
import com.zy.viewinject.ViewLayout;
import com.zy.viewinject.ViewMethod;
import com.zy.viewinject.ZYViewUtils;

import demo.okhttp.zy.com.okhttpdemo.R;


@ViewLayout(R.layout.activity_retrofit)
public class RetrofitActivity extends BaseActivity {

    private final String TAG = "RetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_retrofit);
        ZYViewUtils.register(this);
    }

    @Override
    protected String getLog() {
        return "RetrofitActivity";
    }

    @ViewMethod(getId = R.id.activity_retrofit_test1)
    private void test1(View view){
        Log.d(TAG,"test1:"+title);
        RetrofitManager.request();
    }

    @ViewField(getId = R.id.activity_retrofit_title)
    TextView title;



}
