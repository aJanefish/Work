package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.view.ViewUitls;

public class EventTransferActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_transfer);
        button = findViewById(R.id.activity_event_transfer_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ViewUitls.TAG, "MyView onClick");
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(ViewUitls.TAG, "MyView onTouch");
                return false;
            }
        });

    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean touchEvent = super.dispatchTouchEvent(ev);
        //touchEvent = true;
        Log.d(ViewUitls.TAG, "EventTransferActivity dispatchTouchEvent:" + touchEvent);
        return touchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touchEvent = super.onTouchEvent(event);

        Log.d(ViewUitls.TAG, "EventTransferActivity onTouchEvent:" + touchEvent+" - "+ViewUitls.getAction(event));
        return touchEvent;
    }
}
