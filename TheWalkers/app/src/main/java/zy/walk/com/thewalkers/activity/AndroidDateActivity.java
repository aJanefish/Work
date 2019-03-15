package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;


@ViewLayout(R.layout.activity_menu)
public class AndroidDateActivity extends AppCompatActivity {

    private String TAG = "AndroidDateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_menu);
        ViewUtils.register(this);

    }



    @ViewMethod(getId = R.id.AndroidDateActivity_test1)
    public void test1(View view) {
        SparseArray<String> sparseArray =new SparseArray();
        sparseArray.append(1,"A");
        sparseArray.put(2,"B");
        sparseArray.delete(2);
        Log.d(TAG,""+sparseArray);
    }
}
