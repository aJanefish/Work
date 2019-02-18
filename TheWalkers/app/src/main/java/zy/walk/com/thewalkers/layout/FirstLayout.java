package zy.walk.com.thewalkers.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FirstLayout extends ViewGroup {

    private static final String TAG = "FirstLayout";


    public FirstLayout(Context context) {
        super(context);
    }

    public FirstLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FirstLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FirstLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG,"onLayout :"+this);
        Log.d(TAG,"onLayout changed:"+changed+" l:"+l+" t:"+t+" r:"+r+" b:"+b);
        Log.d(TAG,"onLayout changed:"+changed+" getl:"+getLeft()+" gett:"+getTop()+" getr:"+getRight()+" getb:"+getBottom());
        int childCount = getChildCount();
        Log.d(TAG,"onLayout childCount:"+childCount);


        int x = 0;
        int y = 0;
        int spacing = 100;
        for (int i = 0; i < childCount; i++) {
             View view = getChildAt(i);
             Log.d(TAG,i+" : "+view);

             if((x+1)*100 > getWidth()){
                 y ++;
                 x = 0;
             }

             view.layout(spacing*x,spacing*y,spacing*(x+1),spacing*(y+1));
             Log.d(TAG,i+" : "+view);

             x++;
        }

    }
}
