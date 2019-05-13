package zy.walk.com.thewalkers.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import zy.walk.com.thewalkers.utils.Constant;

public class DiyGroup extends LinearLayout {
    public DiyGroup(Context context) {
        super(context);
    }

    public DiyGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiyGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DiyGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getDiyDefaultSize("DiyGroup onMeasure width", widthMeasureSpec);
        //getDiyDefaultSize("DiyGroup onMeasure height", heightMeasureSpec);
    }


    public static void getDiyDefaultSize(String id, int measureSpec) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id + " - ");
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                stringBuilder.append("UNSPECIFIED - ");
                break;
            case MeasureSpec.AT_MOST:
                stringBuilder.append("EXACTLY - ");
                break;
            case MeasureSpec.EXACTLY:
                stringBuilder.append("AT_MOST - ");
                break;
        }

        stringBuilder.append(specSize + "");
        Log.d(Constant.CREATE_VIEW_LOG, stringBuilder.toString());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(Constant.CREATE_VIEW_LOG, "DiyGroup onLayout:" + changed + " - " + left + " - " + top + " - " + right + " - " + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(Constant.CREATE_VIEW_LOG, "DiyGroup onDraw");

    }
}
