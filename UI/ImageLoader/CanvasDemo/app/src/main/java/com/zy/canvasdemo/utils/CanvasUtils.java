package com.zy.canvasdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class CanvasUtils {
	
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		
		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}
	
	/**
	 * 使用Matrix将Bitmap压缩到指定大小
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h)
	{
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		float scaleWidth = ((float) w) / width;
		float scaleHeight = ((float) h) / height;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}
	
	/**
	 * 给Bitmap设置圆角
	 * @param bitmap
	 * @param roundPx  圆角值
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){
		
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		
		Canvas canvas = new Canvas(output);
		
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		
		return output;
	}
	
	/**
	 * 得到圆形图片
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getOvalBitmap(Bitmap bitmap){
		
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		
		Canvas canvas = new Canvas(output);
		
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		final RectF rectF = new RectF(rect);
		
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		
		canvas.drawOval(rectF, paint);
		
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	
}
