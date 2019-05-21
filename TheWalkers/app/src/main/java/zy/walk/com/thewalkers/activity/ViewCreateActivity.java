package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.view.DiyView;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

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

    @ViewField(R.id.activity_view_create_my_view)
    DiyView myView;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_create);
        ViewUtils.register(this);
    }


    @ViewMethod(R.id.activity_view_create_test1)
    public void test1(View view){
        myView.setRotation(random.nextFloat()*100);
    }



}
