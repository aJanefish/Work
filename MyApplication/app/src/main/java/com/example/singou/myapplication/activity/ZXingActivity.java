package com.example.singou.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
//import com.uuzuche.lib_zxing.encoding.EncodingHandler;

import com.example.singou.myapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;
//import com.google.zxing.qrcode.encoder.;

public class ZXingActivity extends AppCompatActivity {
	
	private static final String TAG = "ZXingActivity";
	private ImageView qr_imageview;

	private static final String URI= "https://uri.amap.com/search?keyword=公交站&center=113.555725,22.212402&city=澳门&view=map&src=mypage&coordinate=gaode&callnative=0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zxing);
		qr_imageview = findViewById(R.id.qr_imageview);

	}
	
	
	public void onCreateQR1(View view) {
		//Bitmap mBitmap = QRCode.createQRCode(uri, 200);
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//容错级别
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			//设置空白边距的宽度
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix encode = new MultiFormatWriter().encode(URI, BarcodeFormat.QR_CODE, wh, wh, hints);
			Bitmap bitmap = createImage(encode, wh, wh, mode);
			setBitmaptoImage(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
	
	
	private static Bitmap createImage(BitMatrix encode, int w, int h, int mode) {
		
		BitMatrix bitMatrix = encode;
		int modeH = mode * h;
		int modeW = mode * w;
		int[] pixels = new int[modeW * modeH];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				
				//x*mode --> 1-mode  y*mode --> 1-mode
				if (bitMatrix.get(x, y)) {
					
					int tmpX = x * mode;
					int tmpY = y * mode;
					for (int x1 = 1; x1 <= mode; x1++) {
						for (int y1 = 1; y1 <= mode; y1++) {
							int tmp = (tmpY + y1) * modeW + (tmpX + x1);
							if (tmp < modeH * modeW) {
								pixels[tmp] = 0xff000000;
							}
						}
					}
					
					//pixels[y * w + x] = 0xff000000;
				} else {
					int tmpX = x * mode;
					int tmpY = y * mode;
					for (int x1 = 1; x1 <= mode; x1++) {
						for (int y1 = 1; y1 <= mode; y1++) {
							int tmp = (tmpY + y1) * modeW + (tmpX + x1);
							if (tmp < modeH * modeW) {
								pixels[tmp] = 0xffffffff;
							}
						}
					}
					//pixels[y * w + x] = 0xffffffff;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(modeW, modeH,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, modeW, 0, 0, modeW, modeH);
		
		return bitmap;
	}
	
	
	
	private int wh = 50;
	private int mode = 6;
	
	public void onCreateQR2(View view) {
		//Bitmap mBitmap = QRCode.createQRCode(uri, 200);
		try {
			
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//容错级别
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			//设置空白边距的宽度
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = new QRCodeWriter().encode(URI, BarcodeFormat.QR_CODE, wh, wh, hints);
			
			//BitMatrix encode = new MultiFormatWriter().encode(uri, BarcodeFormat.QR_CODE, 200, 200,null);
			Bitmap bitmap = createImage(bitMatrix, wh, wh, mode);
			setBitmaptoImage(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
	
	public void onCreateQR11(View view) {
		//Bitmap mBitmap = QRCode.createQRCode(uri, 200);
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//容错级别
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			//设置空白边距的宽度
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix encode = new MultiFormatWriter().encode(URI, BarcodeFormat.QR_CODE, wh, wh, hints);
			Bitmap bitmap = createImage(encode, wh, wh, 1);
			setBitmaptoImage(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
	
	public void onCreateQR22(View view) {
		//Bitmap mBitmap = QRCode.createQRCode(uri, 200);
		try {
			
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//容错级别
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			//设置空白边距的宽度
			hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = new QRCodeWriter().encode(URI, BarcodeFormat.QR_CODE, wh, wh, hints);
			
			//BitMatrix encode = new MultiFormatWriter().encode(uri, BarcodeFormat.QR_CODE, 200, 200,null);
			Bitmap bitmap = createImage(bitMatrix, wh, wh, 1);
			setBitmaptoImage(bitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
	
	private void setBitmaptoImage(Bitmap bitmaptoImage){
		Log.d(TAG,"["+bitmaptoImage.getWidth()+","+bitmaptoImage.getHeight()+"]["+qr_imageview.getWidth()+","+qr_imageview.getHeight()+"]");
		qr_imageview.setImageBitmap(bitmaptoImage);
	}
}
