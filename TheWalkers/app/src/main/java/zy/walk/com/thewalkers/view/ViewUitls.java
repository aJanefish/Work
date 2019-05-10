package zy.walk.com.thewalkers.view;

import android.view.MotionEvent;

public class ViewUitls {

    public static final String TAG = "ViewUitls";

    public static String getAction(MotionEvent event){
        String actoin = null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:actoin = "ACTION_DOWN";break;
            case MotionEvent.ACTION_MOVE:actoin = "ACTION_MOVE";break;
            case MotionEvent.ACTION_UP:actoin = "ACTION_UP";break;
            case MotionEvent.ACTION_CANCEL:actoin = "ACTION_CANCEL";break;
            default:
                break;
        }

        return actoin;
    }

    /**
     * android View 事件传递机制总结
     *
     * */
}
