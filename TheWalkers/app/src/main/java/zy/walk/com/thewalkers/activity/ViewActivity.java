package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.diy.RunBall;

import android.os.Bundle;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        ((RunBall)findViewById(R.id.myrunball1)).setType(1);
        ((RunBall)findViewById(R.id.myrunball2)).setType(2);
        ((RunBall)findViewById(R.id.myrunball3)).setType(3);

        ((RunBall)findViewById(R.id.myrunball4)).setType(4);
        ((RunBall)findViewById(R.id.myrunball5)).setType(5);
        ((RunBall)findViewById(R.id.myrunball6)).setType(6);
        ((RunBall)findViewById(R.id.myrunball7)).setType(7);
        ((RunBall)findViewById(R.id.myrunball8)).setType(8);

        ((RunBall)findViewById(R.id.myrunball9)).setType(9);
    }
}
