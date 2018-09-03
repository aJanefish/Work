package com.example.zy.dragerdemo;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VDHLinearLayout extends LinearLayout {
	ViewDragHelper dragHelper;
	private static final String TAG = "VDHLinearLayout";
	public VDHLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				//tryCaptureView：如果返回true表示捕获相关View，你可以根据第一个参数child决定捕获哪个View
				Log.d(TAG,"tryCaptureView id:"+child.getId() + " pointerId:"+pointerId);
				return child == dragView || child == autoBackView;
				
			}
			
			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				//clampViewPositionVertical：计算child垂直方向的位置，top表示y轴坐标（相对于ViewGroup），默认返回0（如果不复写该方法）。
				//这里，你可以控制垂直方向可移动的范围。
				
				return top;
			}
			
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				//clampViewPositionHorizontal：与clampViewPositionVertical类似，只不过是控制水平方向的位置。
				return left;
			}
			
			// 当前被捕获的View释放之后回调
			@Override
			public void onViewReleased(View releasedChild, float xvel, float yvel) {
				if (releasedChild == autoBackView) {
					Log.d(TAG,autoBackViewOriginLeft+" , "+autoBackViewOriginTop);
					dragHelper.settleCapturedViewAt(autoBackViewOriginLeft, autoBackViewOriginTop);
					invalidate();
				}
			}
			
			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				dragHelper.captureChildView(edgeDragView, pointerId);
			}
			
		});
		// 设置左边缘可以被Drag
		dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
		
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return dragHelper.shouldInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		dragHelper.processTouchEvent(event);
		return true;
	}
	@Override
	public void computeScroll() {
		if (dragHelper.continueSettling(true)) {
			invalidate();
		}
	}
	
	View dragView;
	View edgeDragView;
	View autoBackView;
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		dragView = findViewById(R.id.dragView);
		edgeDragView = findViewById(R.id.edgeDragView);
		autoBackView = findViewById(R.id.autoBackView);
	}
	
	int autoBackViewOriginLeft;
	int autoBackViewOriginTop;
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		autoBackViewOriginLeft = autoBackView.getLeft();
		autoBackViewOriginTop = autoBackView.getTop();
	}
	

}
