package com.example.zy.camerademo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


//The camera preview
//相机预览

public class CameraActivity extends AppCompatActivity {
	
	private static  final String TAG = "CameraActivity";
	private SurfaceView surfaceView;
	private SurfaceHolder holder;
	private ImageView imageView;
	private Camera mCamera;
	private boolean isFront;
	private int mOrientation = 0;
	private long mCameraStartTime;
	private byte[] buffer;
	private int preWidth = 640;
	private int preHeight = 480;
	private int picWidth = 1280;
	private int picHeight = 720;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		
		initView();
		initCamera();
		
		
	}
	
	private void initView() {
		surfaceView = findViewById(R.id.surface);
		imageView = findViewById(R.id.imageview_camera);
		
		
	}
	
	public void takePicture(View view) {
		if (mCamera != null) {
			mCamera.takePicture(null, null, new TakePictureCallback());
		}
	}
	
	private final class TakePictureCallback implements Camera.PictureCallback {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);
			byte[] tempData = os.toByteArray();
			if (tempData != null && tempData.length > 0) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(tempData, 0, tempData.length);
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	
	
	private void initCamera() {
		holder = surfaceView.getHolder();
		holder.addCallback(mSurfaceHolderCallback);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	private SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() {
		@Override
		public void surfaceCreated(SurfaceHolder surfaceHolder) {
			if (mCamera == null) {
				if (Camera.getNumberOfCameras() > 1) {
					mCamera = Camera.open(1);
					isFront = true;
				} else {
					mCamera = Camera.open(0);
					isFront = false;
				}
				Camera.Parameters parameters = mCamera.getParameters();
				
				
				List<Camera.Size>  pictureSizes = parameters.getSupportedPictureSizes();
				int length = pictureSizes.size();
				for (int i = 0; i < length; i++) {
					Log.e(TAG,"SupportedPictureSizes : " + pictureSizes.get(i).width + "x" + pictureSizes.get(i).height);
				}
				
				List<Camera.Size>  previewSizes = parameters.getSupportedPreviewSizes();
				length = previewSizes.size();
				for (int i = 0; i < length; i++) {
					Log.e(TAG,"SupportedPreviewSizes : " + previewSizes.get(i).width + "x" + previewSizes.get(i).height);
				}
				
				buffer = new byte[((preWidth * preHeight) * ImageFormat.getBitsPerPixel(ImageFormat.NV21)) / 8];
				
				parameters.setJpegQuality(80);//照片质量
				
				parameters.setPreviewFrameRate(5);//预览帧率
				
				
				parameters.setPreviewSize(preWidth, preHeight);
				parameters.setPictureSize(picWidth, picHeight);//图片分辨率
				if (parameters.getSupportedFocusModes().contains(
						Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
					parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
				}
				Log.d("CameraManager", "preWidth=" + preWidth + ",preHeight=" + preHeight +
						",picWidth" + picWidth + ",picHeight" + picHeight);
				mCamera.setParameters(parameters);
				mCamera.setDisplayOrientation(mOrientation); // 竖屏时需要该设置
				try {
					mCamera.setPreviewDisplay(holder);
					
					mCamera.addCallbackBuffer(buffer);
					mCamera.setPreviewCallback(mPreviewCallback);
					mCamera.startPreview();
					
					
					Log.d("CameraManager", "打开相机到预览界面时间cost=" + (System.currentTimeMillis() - mCameraStartTime) + "ms");
					mCameraStartTime = System.currentTimeMillis();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int width, int high) {
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	};
	
	int count = 0;
	
	private Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			if (count <= 100) {
				Log.d("mPreviewCallback", "第" + count + "帧的时间cost=" + (System.currentTimeMillis() - mCameraStartTime) + "ms  :" + data.length);
				mCameraStartTime = System.currentTimeMillis();
				count++;
			}

//			if (mCallback != null) {
//				mCallback.onPreview(data, preWidth, preHeight);
//			}
			mCamera.addCallbackBuffer(data);//将此缓冲区添加到预览回调缓冲区队列中
			
			
			ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);
			byte[] tempData = os.toByteArray();
			Log.d("mPreviewCallback","tempData.length start:"+tempData.length+"  "+data.length);
			if (tempData != null && tempData.length > 0) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(tempData, 0, tempData.length);
				
				imageView.setImageBitmap(bitmap);
				Log.d("mPreviewCallback", "width:"+bitmap.getWidth()+"  height:"+bitmap.getHeight());
				
			}
			Log.d("mPreviewCallback","tempData.length end:"+tempData.length+"  "+data.length);
		}
	};
	
	

}
