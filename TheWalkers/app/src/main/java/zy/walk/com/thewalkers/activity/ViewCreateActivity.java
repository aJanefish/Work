package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import zy.walk.com.thewalkers.R;

/**
 *
 * match_parent - AT_MOST
 * wrap_content - EXACTLY
 *
 * 研究View 绘制过程
 *
 * 两次onMeasure -> 一次onLayout -> 一次onDraw
 *
 *
 * */

public class ViewCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_create);

    }


}
