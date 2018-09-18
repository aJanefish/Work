package com.example.zy.universaldemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * 1、多线程异步加载和显示图片（网络图片、sd卡、资源文件（asset，mipmap等，不能加载9patch），新增加载视频缩略图）
 * 2、支持加载过程的监听，可以暂停加载图片，在经常使用的ListView、GridView中，可以设置滑动时暂停加载，停止滑动时加载图片（便于节约流量，在一些优化中可以使用）
 * 3、高度可定制化（可以根据自己的需求进行各种配置，如：线程池，图片下载器，内存缓存策略等）
 * 4、支持图片的内存缓存，SD卡（文件）缓存
 *
 * 注意事项：
 * 1、ImageLoaderConfiguration必须配置并且全局化的初始化这个配置ImageLoader.getInstance().init(config); 否则会出现错误提示
 * 2、ImageLoader是根据ImageView的height，width确定图片的宽高。
 * 3、如果经常出现OOM（官方的建议）
 * ①减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
 * ②使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
 * ③使用.imageScaleType(ImageScaleType.IN_SAMPLE_INT)或者 try.imageScaleType(ImageScaleType.EXACTLY)；
 * ④避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
 * ⑤使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();
 

 * */

public class MainActivity extends AppCompatActivity {
	
	private ImageLoader imageLoader;
	private ImageView myimage;
	private DisplayImageOptions mOptions;
	private String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		init();
	}
	
	private void initView() {
		myimage = findViewById(R.id.myimage);
	}
	
	private void init() {
		//获取缓存文件
		File cacheDir = StorageUtils.getCacheDirectory(this);
		//设置自定义缓存的目录
		cacheDir = StorageUtils.getOwnCacheDirectory(this,"imageloader/Cache");
		//初始化ImageLoad
		ImageLoaderConfiguration config =new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(480,800)//设置缓存图片的默认尺寸,一般取设备的屏幕尺寸
				.diskCacheExtraOptions(480,800, null)
				.threadPoolSize(3)// 线程池内加载的数量,default = 3
				.threadPriority(Thread.NORM_PRIORITY-2)
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2*1024*1024))//自定义内存的缓存策略
				.memoryCacheSize(2*1024*1024)
				.memoryCacheSizePercentage(13)// default
				.diskCache(new UnlimitedDiskCache(cacheDir))// default
				.diskCacheSize(50*1024*1024)
				.diskCacheFileCount(100)//缓存的文件数量
				.diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator())// default
				.imageDownloader(new BaseImageDownloader(this))// default
				.imageDecoder(new BaseImageDecoder(true))// default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
				.writeDebugLogs()
				.build();
		
		ImageLoader.getInstance().init(config);
		
		imageLoader = ImageLoader.getInstance();
		
		mOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.loading)//设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.loaderror)//设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)//设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)//设置是否缓存在SD卡中
				.considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片的缩放类型
				.bitmapConfig(Bitmap.Config.ARGB_4444)//设置图片的解码类型
				//.decodingOptions(null)  //设置Bitmap的配置选项
				.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
				.displayer(new RoundedBitmapDisplayer(100))//是否设置为圆角,弧度为多少
				.displayer(new FadeInBitmapDisplayer(500))//是否图片加载好后渐入的动画时间
				.build();
		/**
		 * imageScaleType(ImageScaleType imageScaleType)是设置图片的缩放方式，其中缩放方式有如下几种：
		 * EXACTLY :图像将完全按比例缩小的目标大小
		 * EXACTLY_STRETCHED:图片会缩放到目标大小完全
		 * IN_SAMPLE_INT:图像将被二次采样的整数倍
		 * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
		 * NONE:图片不会调整
		 *
		 * displayer(BitmapDisplayer displayer)是设置图片的显示方式显示方式displayer：
		 * RoundedBitmapDisplayer（introundPixels）设置圆角图片
		 * FakeBitmapDisplayer（）这个类什么都没做
		 * FadeInBitmapDisplayer（intdurationMillis）设置图片渐显的时间
		 * SimpleBitmapDisplayer()正常显示一张图片
		 
		 * */
		
	}
	
	public void loadOne(View view) {
		// 加载一张网络图片
		imageLoader.displayImage("http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg", myimage);
	}
	
	
	public void loadTwo(View view) {
		//加载一张网络图片并自定义配置
		imageLoader.displayImage(imageUrl,myimage,mOptions);
		
	}
	
	private static  final String imageUrl = "http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg";
	
	public void loadThree(View view) {
		
		ImageLoader.getInstance().displayImage(imageUrl,
				myimage,
				mOptions,
				new ImageLoadingListener() {
					
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						//开始加载
						Log.d(TAG,"onLoadingStarted:"+imageUri);
					}
					
					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						//加载失败
						Log.d(TAG,"onLoadingFailed:"+failReason.getCause());
					}
					
					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						//加载完成
						Log.d(TAG,"onLoadingComplete:");
					}
					
					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						//取消加载
						Log.d(TAG,"onLoadingCancelled:"+imageUri);
					}
				});
	}
	
	
	public void loadFour(View view) {
		imageLoader.displayImage(imageUrl,
				myimage,
				mOptions,
				new ImageLoadingListener() {
					
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						//开始加载
						Log.d(TAG,"onLoadingStarted:"+imageUri);
					}
					
					@Override
					public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
						//加载失败
						Log.d(TAG,"onLoadingFailed:"+failReason.getCause());
					}
					
					@Override
					public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
						//加载完成
						Log.d(TAG,"onLoadingComplete:");
					}
					
					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						//取消加载
						Log.d(TAG,"onLoadingCancelled:"+imageUri);
					}
				},
				new ImageLoadingProgressListener(){
					
					@Override
					public void onProgressUpdate(String imageUri, View view, int current, int total) {
					
					}
				}
			);
			

	}
}
