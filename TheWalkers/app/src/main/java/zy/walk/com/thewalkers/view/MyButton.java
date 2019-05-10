package zy.walk.com.thewalkers.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touchEvent = super.onTouchEvent(event);
        //touchEvent = true;
        Log.d(ViewUitls.TAG, "MyView onTouchEvent:" + touchEvent+" - "+ViewUitls.getAction(event));
        return touchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean touchEvent = super.dispatchTouchEvent(event);
        //touchEvent = false;
        Log.d(ViewUitls.TAG, "MyView dispatchTouchEvent:" + touchEvent);
        return touchEvent;
    }

}
