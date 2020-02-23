package zy.walk.com.thewalkers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class GroupB extends LinearLayout {
    public GroupB(Context context) {
        super(context);
    }

    public GroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupB(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GroupB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(ViewUitls.TAG, "GroupB onInterceptTouchEvent start");

        boolean interceptTouchEvent = super.onInterceptTouchEvent(ev);
        //interceptTouchEvent = true;
        Log.d(ViewUitls.TAG, "GroupB onInterceptTouchEvent end:" + interceptTouchEvent);
        return interceptTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(ViewUitls.TAG, "GroupB dispatchTouchEvent start");
        boolean touchEvent = super.dispatchTouchEvent(ev);
        //touchEvent = false;
        Log.d(ViewUitls.TAG, "GroupB dispatchTouchEvent end:" + touchEvent);
        return touchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(ViewUitls.TAG, "GroupB onTouchEvent start" );

        boolean touchEvent = super.onTouchEvent(event);
        //touchEvent = true;
        Log.d(ViewUitls.TAG, "GroupB onTouchEvent end:" + touchEvent + " - " + ViewUitls.getAction(event));
        return touchEvent;
    }
}
