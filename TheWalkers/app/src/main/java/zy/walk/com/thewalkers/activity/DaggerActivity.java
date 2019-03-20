package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.dagger.User;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

@ViewLayout(R.layout.activity_dagger)
public class DaggerActivity extends BaseActivity {
    private static final String TAG = "DaggerActivity";
    @ViewField(getId = R.id.activity_dagger_title)
    TextView title;

    @ViewField(getId = R.id.activity_dagger_des)
    TextView des;
    @ViewField(getId = R.id.activity_dagger_test)
    Button button_test;

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dagger);
        ViewUtils.register(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    protected String getLog() {
        return TAG;
    }


    @ViewMethod(getId = R.id.activity_dagger_test)
    private void test(View view){
        Log.d(TAG,"User:"+user);
    }
}
