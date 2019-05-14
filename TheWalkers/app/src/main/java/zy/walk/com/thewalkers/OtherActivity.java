package zy.walk.com.thewalkers;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(MyApp.TAG, "OtherActivity finalize " + this);
    }
}
