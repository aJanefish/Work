package zy.walk.com.thewalkers.imagesandanimations;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import zy.walk.com.thewalkers.R;

public class BitmapUtils {
    private static String TAG = "BitmapUtils";

    /**高效加载大图
     *
     *
     *
     * */

    public static Bitmap loadbitmap(Context context){
        Bitmap rawBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.gril);
        Log.d(TAG,"rawBitmap:"+rawBitmap.getWidth()+" , "+rawBitmap.getHeight());
        return rawBitmap;
    }



}
