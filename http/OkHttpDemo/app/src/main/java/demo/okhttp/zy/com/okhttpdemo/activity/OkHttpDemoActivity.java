package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zy.viewinject.ViewField;
import com.zy.viewinject.ZYViewUtils;

import demo.okhttp.zy.com.okhttpdemo.R;
import demo.okhttp.zy.com.okhttpdemo.presenter.OkHttpPresenter;
import demo.okhttp.zy.com.okhttpdemo.view.IOkhttpView;

public class OkHttpDemoActivity extends AppCompatActivity implements IOkhttpView {

    @ViewField( getId = R.id.activity_ok_http_demo_recycler_view)
    private RecyclerView recycler_view;
    private OkHttpPresenter httpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);
        ZYViewUtils.register(this);
        httpPresenter = new OkHttpPresenter(this);

    }

}
