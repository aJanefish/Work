package zy.walk.com.thewalkers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class GroupA extends LinearLayout {
    public GroupA(Context context) {
        super(context);
    }

    public GroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GroupA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interceptTouchEvent = super.onInterceptTouchEvent(ev);
        //interceptTouchEvent = true;
        Log.d(ViewUitls.TAG, "GroupA onInterceptTouchEvent:" + interceptTouchEvent);
        return interceptTouchEvent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean touchEvent = super.dispatchTouchEvent(ev);
        //touchEvent = true;
        Log.d(ViewUitls.TAG, "GroupA dispatchTouchEvent:" + touchEvent);
        return touchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touchEvent = super.onTouchEvent(event);
        Log.d(ViewUitls.TAG, "GroupA onTouchEvent:" + touchEvent+" - "+ViewUitls.getAction(event));
        return touchEvent;
    }
}
