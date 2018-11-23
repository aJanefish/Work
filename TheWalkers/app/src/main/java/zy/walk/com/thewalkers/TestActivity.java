package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.newwork.Main2Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void Photo(View view) {
        startActivity(new Intent(TestActivity.this,Main2Activity.class));
    }
}
