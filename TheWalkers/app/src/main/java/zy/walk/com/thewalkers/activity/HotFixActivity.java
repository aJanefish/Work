package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.hotfix.BugClass;

public class HotFixActivity extends AppCompatActivity {

    private static final String TAG = "HotFixActivity";
    private TextView hot_fix_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        hot_fix_text_view = findViewById(R.id.activity_hot_fix_text_view);
    }

    public void HotFix(View view) {
        hot_fix_text_view.setText(BugClass.getBug());
        Log.d(TAG,""+ Arrays.toString(fileList()));

    }
}
