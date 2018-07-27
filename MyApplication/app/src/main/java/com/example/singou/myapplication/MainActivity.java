package com.example.singou.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.singou.myapplication.activity.DebugActvity;
import com.example.singou.myapplication.activity.GlideActivity;
import com.example.singou.myapplication.activity.SPPXActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDebug(View view) {
        startActivity(new Intent(MainActivity.this,DebugActvity.class));
    }
    
    public void onSnackbar(View view) {
        Toast toast = Toast.makeText(this,"nihao",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }
    
    public void onGlide(View view) {
        startActivity(new Intent(MainActivity.this,GlideActivity.class));
    }
    
    public void onText(View view) {
        startActivity(new Intent(MainActivity.this,SPPXActivity.class));
    }
}
