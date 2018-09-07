package com.example.singou.myapplication.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {
	public static int pixelToDp(Context context, int pixel) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
	}
	public static int dpToPixel(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
	}
}

