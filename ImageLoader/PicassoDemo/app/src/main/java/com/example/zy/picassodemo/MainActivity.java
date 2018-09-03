package com.example.zy.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * https://github.com/square/picasso
 * */

public class MainActivity extends AppCompatActivity {
	
	private ImageView myimage;
	private String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myimage = findViewById(R.id.myimage);
	}
	
	public void one(View view) {
		Picasso.with(this)
				.load(Constant.picUrl)
				//加载过程中的图片显
				.placeholder(R.drawable.loading)
				//加载失败中的图片显示
				// 如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
				.error(R.drawable.loaderror)
				.into(myimage);
		
		
		//加载资源文件
		//Picasso.with(this).load(R.drawable.landing_screen).into(imageView1);
		//加载本地文件
		//Picasso.with(this).load(new File("/images/oprah_bees.gif")).into(imageView2);
		
		Log.d(TAG,"one："+Constant.picUrl);
	}
	
	public void two(View view) {
		//设置加载的图片大小
		Picasso.with(this)
				.load(Constant.picUrl)
				//加载过程中的图片显示
				.placeholder(R.drawable.loading)
				//加载失败中的图片显示
				// 如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
				.error(R.drawable.loaderror)
				//裁剪图片尺寸
				.resize(300, 300)
				//设置图片圆角
				.centerCrop()
				.into(myimage);
	}
	
	
	public void three(View view) {
		//.noFade()：
		// Picasso的默认图片加载方式有一个淡入的效果，如果调用了noFade()方法后加载的图片将直接显示在ImageView上
		Picasso.with(this)
				.load(Constant.picUrl1)
				//加载过程中的图片显
				.placeholder(R.drawable.loading)
				//加载失败中的图片显示
				// 如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
				.error(R.drawable.loaderror)
				.noFade()
				.into(myimage);
	}
	
	public void four(View view) {
		//4. .onlyScaleDown()：
		//
		//如果调用了resize(width,height)方法的话，Picasso一般会重新计算以改变图片的加载质量，
		// 比如一张小图变成一张大图进行展示的时候。但是如果我们的原图是比重新resize的新图规格大的时候，
		// 我们就可以调用onlyScaleDown()来直接进行展示而不再重新计算，缩短图片的加载计算时间。
		
		Picasso.with(this)
				.load(Constant.picUrl)
				//加载过程中的图片显
				.placeholder(R.drawable.loading)
				//加载失败中的图片显示
				// 如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
				.error(R.drawable.loaderror)
				.resize(400, 400)
				.onlyScaleDown()
				.into(myimage);
	}
	
	public void five(View view) {
		//5. .centerInside()：
		//图片会被完整的展示，可能图片不会填充满ImageView，也有可能会被拉伸或者挤压，一般是等比例缩小。
		//等比例缩放
		Picasso.with(this)
				.load(Constant.picUrl)
				//加载过程中的图片显
				.placeholder(R.drawable.loading)
				//加载失败中的图片显示
				// 如果重试3次（下载源代码可以根据需要修改）还是无法成功加载图片，则用错误占位符图片显示。
				.error(R.drawable.loaderror)
				.resize(400, 400)
				.centerInside()
				.into(myimage);
	}
	//6. .centerCrop()：
	//
	//图片会被裁剪，但是图片质量没有什么区别，等比例放大。
	//
	//7. .fit()：
	//
	//Picasso会对图片的大小及ImageView进行测量，计算出最佳的大小及最佳的图片质量来进行图片展示，减少内存的消耗并对视图没有影响。
	//
	//8. .priority()：
	//
	//如果一个视图中顶部图片较大而底部图片较小，因为Picasso是异步加载，所以小图会先加载出来。
	// 但是对于用户来说或许更希望看到的是上面的图片先被加载而底部的图片后被加载，此时可使用此方法来设置图片加载的优先级。
	//注意：设置优先级并不能保证图片就一定会被优先加载，只是会偏向倾斜于先加载。
	//
	//9. .tag()：
	//
	//为请求添加标记提升用户体验。比如在列表ListView的Item中加载了图片，当用户在快速滑动的时候可以设置停止请求，在滑动停止时再去加载请求，退出当前页面时取消请求。
	
	//10. .fetch()：
	//用于初始化缓存
	//该方法会在后台异步加载一张图片，但是不会展示在ImageView上，也不会返回Bitmap，这个方法只是为了将获取到的资源加载到本地和内存当中，为后期的加载缩短时间。
}
