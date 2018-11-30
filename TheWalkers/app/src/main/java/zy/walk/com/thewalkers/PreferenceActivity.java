package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.preference.DemoPreferenceFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
    }

    public void test(View view) {
        Intent intent = new Intent(this, DemoPreferenceFragment.class);
        startActivity(intent);
    }
}
