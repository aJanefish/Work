package demo.dui.zy.com.duiwakeupdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WakeUpAsrActivity extends AppCompatActivity {

    private WakeUpManager wakeUpManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up_asr);

        wakeUpManager = new WakeUpManager();

    }

    public void wakeup(View view) {
        wakeUpManager.start();
    }



}
