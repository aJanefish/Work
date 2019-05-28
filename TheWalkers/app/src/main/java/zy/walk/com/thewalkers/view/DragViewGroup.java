package zy.walk.com.thewalkers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import zy.walk.com.thewalkers.R;


/**
 * 拖动ViewGroup
 */
public class DragViewGroup extends ViewGroup {
    private static final String TAG = "DragViewGroup";

    public DragViewGroup(Context context) {
        super(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //getDefaultSize();
        ViewParent parent = getParent();
//        show("onMeasure parent:" + parent);
//        show("onMeasure this:" + this);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ViewParent parent = getParent();
        show("onLayout parent:" + parent);
        show("onLayout this:" + this);
        int count = getChildCount();
        show("count:" + count);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            //if (child.getVisibility() == GONE) continue;
            show("view:" + child);
            show(child.getWidth() + " - " + child.getHeight() + " - " + child.getMeasuredWidth() + " - " + child.getMeasuredHeight());
            //view.layout(0,0,120,120);

            layoutChild(child);

        }
    }


    private void layoutChild(View child) {
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        //child.setLayoutParams();
        show("layoutParams:" + layoutParams);

        int centerX = calculateCenterX(layoutParams);
        int centerY = calculateCenterY(layoutParams);
        int w = layoutParams.width / 2;
        int h = layoutParams.height / 2;
        //int l, int t, int r, int b
        child.layout(centerX - w, centerY - h, centerX + w, centerY + h);
    }

    private int calculateCenterX(LayoutParams layoutParams) {
        return 150 / 2 + 150 * layoutParams.launcherX;
    }

    private int calculateCenterY(LayoutParams layoutParams) {
        return 150 / 2 + 150 * layoutParams.launcherY;
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        show("LayoutParams create generateLayoutParams");
        return new DragViewGroup.LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        show("LayoutParams create generateDefaultLayoutParams");
        return new DragViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    // Override to allow type-checking of LayoutParams.
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        show("LayoutParams create checkLayoutParams");
        return p instanceof DragViewGroup.LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
//        if (sPreserveMarginParamsInLayoutParamConversion) {
//            if (lp instanceof LayoutParams) {
//                return new LayoutParams((LayoutParams) lp);
//            } else if (lp instanceof MarginLayoutParams) {
//                return new LayoutParams((MarginLayoutParams) lp);
//            }
//        }
        show("LayoutParams create generateLayoutParams");
        return new DragViewGroup.LayoutParams(lp);
    }

    static class LayoutParams extends ViewGroup.MarginLayoutParams {

        private int launcherX;
        private int launcherY;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DragViewGroup_Launcher);

            launcherX = a.getInt(R.styleable.DragViewGroup_Launcher_launcherX, -1);
            launcherY = a.getInt(R.styleable.DragViewGroup_Launcher_launcherY, -1);

            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
        }


        @Override
        public String toString() {
            return "LayoutParams{" +
                    "launcherX=" + launcherX +
                    ", launcherY=" + launcherY +
                    ", height=" + height +
                    ", width=" + width +
                    '}';
        }
    }


    private void show(String values) {
        Log.d(TAG, "" + values);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean touchEvent = super.dispatchTouchEvent(ev);
        //show("dispatchTouchEvent touchEvent:" + touchEvent);
        return touchEvent;
    }

    private View dragView = null;

    private void handleDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        show("Down:[" + x + " - " + y + "]");
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (isRect(x, y, layoutParams)) {
                show("layoutParams:" + layoutParams + " - " + child);
                child.setVisibility(INVISIBLE);
                dragView = child;
                return;
            }
        }
    }

    private boolean isRect(float x, float y, LayoutParams layoutParams) {
        int launcherX_Min = layoutParams.launcherX * 150;
        int launcherX_Max = layoutParams.launcherX * 150 + 150;

        int launcherY_Min = layoutParams.launcherY * 150;
        int launcherY_Max = layoutParams.launcherY * 150 + 150;

        return x >= launcherX_Min && x <= launcherX_Max && y >= launcherY_Min && y <= launcherY_Max;
    }

    private void handleUp(MotionEvent event) {
        if (dragView != null) {
            dragView.setVisibility(VISIBLE);
            float x = event.getX();
            float y = event.getY();
            show("Up:[" + x + " - " + y + "][" + getWidth() + " - " + getHeight() + "]");
            //获得 launcherX launcherY
            int launcherX = calculateLauncher(x);
            int launcherY = calculateLauncher(y);
            show("launcher [" + launcherX + "-" + launcherY + "]");
            LayoutParams layoutParams = (LayoutParams) dragView.getLayoutParams();
            layoutParams.launcherX = launcherX;
            layoutParams.launcherY = launcherY;
            layoutChild(dragView);
            dragView = null;
        }
    }

    /**
     * 150
     */
    private int calculateLauncher(float x) {
        if (x <= 0) return 0;
        else if (x >= getWidth()) return getWidth() / 150 - 1;
        else {

            if (x <= 150) return 0;
            else if (x <= 300) return 1;
            else if (x <= 450) return 2;
            else if (x <= 600) return 3;
            else if (x <= 750) return 4;
            else return 5;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touch = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                show("ACTION_DOWN");
                handleDown(event);
                touch = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //show("ACTION_MOVE");
                touch = super.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                touch = super.onTouchEvent(event);
                show("ACTION_UP");
                handleUp(event);
                break;
            case MotionEvent.ACTION_CANCEL:
                show("ACTION_CANCEL");
                touch = super.onTouchEvent(event);

                break;
        }
        //show("onTouchEvent touch:" + touch);
        return touch;
    }


}
