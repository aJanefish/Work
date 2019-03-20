package zy.walk.com.thewalkers.activity;



import okhttp3.OkHttpClient;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.dagger.DaggerUserComponent;
import zy.walk.com.thewalkers.dagger.Person;
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
import javax.inject.Named;

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

    @Inject
    User user1;

    @Named("release")
    @Inject
    Person person;

    @Named("debug")
    @Inject
    Person person1;



    @Inject
    OkHttpClient okHttpClient;


    @Inject
    OkHttpClient okHttpClient1;

    @Inject
    OkHttpClient okHttpClient2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dagger);
        ViewUtils.register(this);
        DaggerUserComponent.builder().build().inject(this);
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
    private void test(View view) {
        Log.d(TAG, "" + title + "\n" + des + "\n" + button_test);
        Log.d(TAG, "User:" + user);
        Log.d(TAG, "User1:" + user1);
        Log.d(TAG, "person:" + person);
        Log.d(TAG, "person1:" + person1);

        Log.d(TAG, "okHttpClient:" + okHttpClient);
        Log.d(TAG, "okHttpClient:" + okHttpClient1);

        Log.d(TAG, "okHttpClient:" + okHttpClient2);

    }
}
