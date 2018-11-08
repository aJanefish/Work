package com.example.zy.rxjavademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;
//所有的异步都可以用RxJava来做就可以了，尤其是复杂的场景，越是复杂的场景越能体现RxJava的好处
//
/**
 * 26 window
 * 25 scan
 * 24 reduce
 * 23 merge
 * 22 last
 * 21 defer
 * 20 debounce
 * 19 Single
 * 18 take
 * 17 skip
 * 16 doOnNext
 * 15 interval
 * 14 timer
 * 13 buffer
 * 12 Filter
 * 11 distinct
 * 10 concatMap
 * 9 FlatMap
 * 8 Concat
 * 7 Zip
 * 6 map
 * 5
 * 4
 * 3
 * 2
 * 1
 * */

public class Utils {
	private static String TAG = "Utils";
	private static Disposable mDisposable;
	
	private static int[] drawableRes =new int[]{R.drawable.amap_bus,R.drawable.amap_car,R.drawable.amap_man,
			R.drawable.amap_ride,R.drawable.app_guide_broadcast_nor,R.drawable.app_guide_map_nor,
			R.drawable.app_guide_beauty_nor,R.drawable.app_guide_music_nor,R.drawable.app_guide_news_nor,
			R.drawable.app_guide_note_nor};
	
	public static void demo26() {
		//window
		//按照实际划分窗口，将数据发送给不同的 Observable
		
		
		Log.e(TAG, "window\n");
		Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
				.take(15) // 最多接收15个
				.window(3, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Observable<Long>>() {
					@Override
					public void accept(@NonNull Observable<Long> longObservable) throws Exception {
						
						Log.e(TAG, "Sub Divide begin...\n"+longObservable);
						longObservable.subscribeOn(Schedulers.io())
								.observeOn(AndroidSchedulers.mainThread())
								.subscribe(new Consumer<Long>() {
									@Override
									public void accept(@NonNull Long aLong) throws Exception {
										
										Log.e(TAG, "Next:" + aLong + "\n");
									}
								});
					}
				});
		

	}
	
	public static void demo25() {
		//scan
		//scan 操作符作用和上面的 reduce 一致，唯一区别是 reduce 是个只追求结果的坏人，
		// 而  scan 会始终如一地把每一个步骤都输出。
		Observable.just(1, 2, 3,4,5)
				.scan(new BiFunction<Integer, Integer, Integer>() {
					@Override
					public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
						return integer + integer2;
					}
				}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				
				Log.e(TAG, "accept: scan " + integer + "\n");
			}
		});
		

	}
	
	public static void demo24() {
		//reduce
		//reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
		
		Observable.just(1, 2, 3,4)
				.reduce(new BiFunction<Integer, Integer, Integer>() {
					@Override
					public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
						return integer + integer2;
					}
				}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				Log.e(TAG, "accept: reduce : " + integer + "\n");
			}
		});

	}
	
	public static void demo23() {
		//merge
		//merge 顾名思义，熟悉版本控制工具的你一定不会不知道 merge 命令，
		// 而在 Rx 操作符中，merge 的作用是把多个 Observable 结合起来，接受可变参数，也支持迭代器集合。
		// 注意它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
		Observable.merge(Observable.just(1, 2,6,7,8), Observable.just(3, 4, 5))
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						Log.e(TAG, "accept: merge :" + integer + "\n" );
					}
				});

	}
	
	public static void demo22(){
		//last
		//last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
		Observable.just(1, 2, 3,4,5,6)
				.last(4)
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("last : " + integer + "\n");
						Log.e(TAG, "last : " + integer + "\n");
					}
				});
		
	}
	
	
	public static void demo21(){
		//defer
		//简单地时候就是每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
		Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
			@Override
			public ObservableSource<Integer> call() throws Exception {
				return Observable.just(1, 2, 3);
			}
		});
		
		
		observable.subscribe(new Observer<Integer>() {
			@Override
			public void onSubscribe(@NonNull Disposable d) {
			
			}
			
			@Override
			public void onNext(@NonNull Integer integer) {
				
				Log.e(TAG, "defer : " + integer + "\n");
			}
			
			@Override
			public void onError(@NonNull Throwable e) {
				
				Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
			}
			
			@Override
			public void onComplete() {
				
				Log.e(TAG, "defer : onComplete\n");
			}
		});
		
		
		
		
	}
	
	public static void demo20(){
		//debounce
		//去除发送频率过快的项，看起来好像没啥用处，但你信我，后面绝对有地方很有用武之地。
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
				// send events with simulated time wait
				emitter.onNext(1); // skip
				Thread.sleep(400);
				emitter.onNext(2); // deliver
				Thread.sleep(505);
				emitter.onNext(3); // skip
				Thread.sleep(100);
				emitter.onNext(4); // deliver
				Thread.sleep(605);
				emitter.onNext(5); // deliver
				Thread.sleep(510);
				emitter.onComplete();
			}
		}).debounce(500, TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						Log.e(TAG,"debounce :" + integer + "\n");
					}
				});
		

		
		
	}
	
	public static void demo19(){
		//Single
		//顾名思义，Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
		Single.just(new Random().nextInt())
				.subscribe(new SingleObserver<Integer>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {
						Log.e(TAG, "single : onSubscribe : \n" );
					}
					
					@Override
					public void onSuccess(@NonNull Integer integer) {
						
						Log.e(TAG, "single : onSuccess : "+integer+"\n" );
					}
					
					@Override
					public void onError(@NonNull Throwable e) {
						
						Log.e(TAG, "single : onError : "+e.getMessage()+"\n");
					}
				});
		


		
		
	}
	
	public static void demo18(){
		//take
		//take，接受一个 long 型参数 count ，代表至多接收 count 个数据。
		Observable.just(1,2,3,4,5)
				.take(3)
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("skip : "+integer + "\n");
						Log.e(TAG, "take : "+integer + "\n");
					}
				});
		
		
	}
	
	public static void demo17(){
		//skip
		//skip 很有意思，其实作用就和字面意思一样，接受一个 long 型参数 count ，代表跳过 count 个数目开始接收。
		Observable.just(1,2,3,4,5)
				.skip(2)
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("skip : "+integer + "\n");
						Log.e(TAG, "skip : "+integer + "\n");
					}
				});
		

	}
	
	public static void demo16() {
		//doOnNext
		//其实觉得 doOnNext 应该不算一个操作符，但考虑到其常用性，我们还是咬咬牙将它放在了这里。
		// 它的作用是让订阅者在接收到数据之前干点有意思的事情。
		// 假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
		
		Observable.just(1, 2, 3, 4)
				.doOnNext(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("doOnNext 保存 " + integer + "成功" + "\n");
						Log.e(TAG, "doOnNext 保存 " + integer + "成功" + "\n");
					}
				}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				//mRxOperatorsText.append("doOnNext :" + integer + "\n");
				Log.e(TAG, "doOnNext :" + integer + "\n");
			}
		});
		

	
	
	
	}
	
	public static void demo15() {
		//interval
		//如同我们上面可说，interval 操作符用于间隔时间执行某个操作，
		// 其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
		
		Log.e(TAG, "interval start : " + System.currentTimeMillis() + "\n");
		mDisposable  = Observable.interval(3,2, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(@NonNull Long aLong) throws Exception {
						//mRxOperatorsText.append("interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
						Log.e(TAG, "interval :" + aLong + " at " + System.currentTimeMillis() + "\n");
						
						if (aLong == 10) {
							
							if (mDisposable != null && !mDisposable.isDisposed()) {
								mDisposable.dispose();
								Log.e(TAG, "end");
							}
						}
					}
				});
		

		
	}
	
	
	public static void demo14() {
		//timer
		//timer 很有意思，相当于一个定时任务。
		// 在 1.x 中它还可以执行间隔逻辑，
		// 但在 2.x 中此功能被交给了 interval，下一个会介绍。
		// 但需要注意的是，timer 和 interval 均默认在新线程。
		
		Log.e(TAG, "timer start : " + System.currentTimeMillis() + "\n");
		Observable.timer(2, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(@NonNull Long aLong) throws Exception {
						Log.e(TAG, "timer :" + aLong + " at " + System.currentTimeMillis() + "\n");
					}
				});
		

		
	}
	
	
	public static void demo13() {
		//buffer 操作符接受两个参数，buffer(count,skip)，
		// 作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，
		// 然后生成一个  Observable
		
		Observable.just(1, 2, 3, 4, 5,6,7,8)
				.buffer(2, 3)
				.subscribe(new Consumer<List<Integer>>() {
					@Override
					public void accept(@NonNull List<Integer> integers) throws Exception {
						Log.e(TAG, "buffer size : " + integers.size() + "\n");
						Log.e(TAG, "buffer value : " +integers);
						
						Log.e(TAG, "\n");
					}
				});
	
	}
	
	
	public static void demo12() {
		//Filter
		//信我，Filter 你会很常用的，它的作用也很简单，过滤器嘛。可以接受一个参数，让其过滤掉不符合我们条件的值
		
		Observable.just(1, 20, 65, -5, 7, 19)
				.filter(new Predicate<Integer>() {
					@Override
					public boolean test(@NonNull Integer integer) throws Exception {
						return integer >= 10;
					}
				}).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				//mRxOperatorsText.append("filter : " + integer + "\n");
				Log.e(TAG, "filter : " + integer + "\n");
			}
		});
		

	}
	
	
	public static void demo11() {
		//distinct
		//这个操作符非常的简单、通俗、易懂，就是简单的去重嘛，我甚至都不想贴代码，但人嘛，总得持之以恒。
		Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
				.distinct()
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("distinct : " + integer + "\n");
						Log.e(TAG, "distinct : " + integer + "\n");
					}
				});
		

	}
	
	
	
	public static void demo10() {
		//上面其实就说了，concatMap 与 FlatMap 的唯一区别就是 concatMap 保证了顺序，
		// 所以，我们就直接把 flatMap 替换为 concatMap 验证吧。
		//
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				e.onNext(1);
				e.onNext(2);
				e.onNext(3);
			}
		}).concatMap(new Function<Integer, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
				List<String> list = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					list.add("I am value " + integer);
				}
				int delayTime = (int) (1 + Math.random() * 10);
				return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
			}
		}).subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(@NonNull String s) throws Exception {
						Log.e(TAG, "concatMap : accept : " + s + "\n");
						//mRxOperatorsText.append("flatMap : accept : " + s + "\n");
					}
				});
		
	}
	
	public static void demo9() {
		//FlatMap
		//FlatMap 是一个很有趣的东西，我坚信你在实际开发中会经常用到。
		// 它可以把一个发射器  Observable 通过某种方法转换为多个 Observables，
		// 然后再把这些分散的 Observables装进一个单一的发射器 Observable。
		// 但有个需要注意的是，flatMap 并不能保证事件的顺序，
		// 如果需要保证，需要用到我们下面要讲的 ConcatMap。
		//
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				e.onNext(1);
				e.onNext(2);
				e.onNext(3);
			}
		}).flatMap(new Function<Integer, ObservableSource<String>>() {
			@Override
			public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
				List<String> list = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					list.add("I am value " + integer);
				}
				int delayTime = (int) (1 + Math.random() * 10);
				return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
			}
		}).subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(@NonNull String s) throws Exception {
						Log.e(TAG, "flatMap : accept : " + s + "\n");
						//mRxOperatorsText.append("flatMap : accept : " + s + "\n");
					}
				});
		
	}
	
	
	public static void demo8() {
		//Concat
		//对于单一的把两个发射器连接成一个发射器，虽然 zip 不能完成，
		//但我们还是可以自力更生，官方提供的 concat 让我们的问题得到了完美解决。
		
		Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(@NonNull Integer integer) throws Exception {
						//mRxOperatorsText.append("concat : "+ integer + "\n");
						Log.e(TAG, "concat : "+ integer + "\n" );
					}
				});
		

		
	}
	
	
	
	private static Observable<String> getStringObservable() {
		return Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
				if (!e.isDisposed()) {
					e.onNext("A");
					//mRxOperatorsText.append("String emit : A \n");
					Log.e(TAG, "String emit : A \n");
					e.onNext("B");
					//mRxOperatorsText.append("String emit : B \n");
					Log.e(TAG, "String emit : B \n");
					e.onNext("C");
					//mRxOperatorsText.append("String emit : C \n");
					Log.e(TAG, "String emit : C \n");
				}
			}
		});
	}
	
	private static Observable<Integer> getIntegerObservable() {
		return Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				if (!e.isDisposed()) {
					e.onNext(1);
					//mRxOperatorsText.append("Integer emit : 1 \n");
					Log.e(TAG, "Integer emit : 1 \n");
					e.onNext(2);
					//mRxOperatorsText.append("Integer emit : 2 \n");
					Log.e(TAG, "Integer emit : 2 \n");
					e.onNext(3);
					//mRxOperatorsText.append("Integer emit : 3 \n");
					Log.e(TAG, "Integer emit : 3 \n");
					e.onNext(4);
					//mRxOperatorsText.append("Integer emit : 4 \n");
					Log.e(TAG, "Integer emit : 4 \n");
					e.onNext(5);
					//mRxOperatorsText.append("Integer emit : 5 \n");
					Log.e(TAG, "Integer emit : 5 \n");
				}
			}
		});
	}
	

	
	public static void demo7() {
		//Zip
		//zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，
		// 并且一个事件只能被使用一次，组合的顺序是严格按照事件发送的顺序来进行的，
		// 所以上面截图中，可以看到，1 永远是和 A 结合的，2 永远是和 B 结合的。
		//
		//最终接收器收到的事件数量是和发送器发送事件最少的那个发送器的发送事件数目相同，
		// 所以如截图中，5 很孤单，没有人愿意和它交往，孤独终老的单身狗。

		Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
			@Override
			public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
				return s + "--" +integer;
			}
		}).subscribe(new Consumer<String>() {
			@Override
			public void accept(@NonNull String s) throws Exception {
				//mRxOperatorsText.append("zip : accept : " + s + "\n");
				Log.e(TAG, "zip : accept : " + s + "\n");
			}
		});
		

	}
	
	
	public static void demo6() {
		//map
		//它的作用是对发射时间发送的每一个事件应用一个函数，是的每一个事件都按照指定的函数去变化，而在 2.x 中它的作用几乎一致
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				e.onNext(1);
				e.onNext(2);
				e.onNext(3);
			}
		}).map(new Function<Integer, String>() {
			@Override
			public String apply(@NonNull Integer integer) throws Exception {
				return "This is result " + integer;
			}
		}).subscribe(new Consumer<String>() {
			@Override
			public void accept(@NonNull String s) throws Exception {
				//mRxOperatorsText.append("accept : " + s +"\n");
				Log.e(TAG, "accept : " + s + "\n");
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
			
			}
		}, new Action() {
			@Override
			public void run() throws Exception {
			
			}
		});
		
		
	}
	
	
	
	public static void demo5() {
		
		Observable.create(new ObservableOnSubscribe<Integer>() { // 第一步：初始化Observable
			@Override
			public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
				Log.e(TAG, "Observable emit 1" + "\n");
				e.onNext(1);
				Log.e(TAG, "Observable emit 2" + "\n");
				e.onNext(2);
				Log.e(TAG, "Observable emit 3" + "\n");
				e.onNext(3);
				//e.onComplete();
				Log.e(TAG, "Observable emit 4" + "\n" );
				e.onNext(4);
				//e.onComplete();
			}
		})
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(new Observer<Integer>() { // 第三步：订阅
			
			// 第二步：初始化Observer
			private int i;
			private Disposable mDisposable;
			
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				mDisposable = d;
			}
			
			@Override
			public void onNext(@NonNull Integer integer) {
				i++;
				if (i == 2) {
					// 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
					//mDisposable.dispose();
				}
				Log.e(TAG,"onNext:"+integer);
			}
			
			@Override
			public void onError(@NonNull Throwable e) {
				Log.e(TAG, "onError : value : " + e.getMessage() + "\n" );
			}
			
			@Override
			public void onComplete() {
				Log.e(TAG, "onComplete" + "\n" );
			}
		});
		
	
	}
	
	public static void demo4(final Context context) {
		
		Observable.create(new ObservableOnSubscribe<Drawable>() {
			@Override
			public void subscribe(ObservableEmitter<Drawable> emitter) throws Exception {
				for (int i=0;i<drawableRes.length;i++){
					Drawable drawable= context.getResources().getDrawable(drawableRes[i]);
					//第6个图片延时3秒后架子
					if (i==5){
						sleep(3000);
					}
					//复制第7张图片到sd卡
					if (i==6){
						Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
						saveBitmap(bitmap,"test.png", Bitmap.CompressFormat.PNG);
					}
					//上传到网络
					if (i==7){
						updateIcon(drawable);
					}
					emitter.onNext(drawable);
				}
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Drawable>() {
					@Override
					public void accept(Drawable drawable) throws Exception {
						//回调后在UI界面上展示出来
						Log.d(TAG,"accept:"+drawable);
					}
				});
	
	}
	private static void updateIcon(Drawable drawable) {
	}
	
	
	/**
	 * 将Bitmap以指定格式保存到指定路径
	 * @param bitmap
	 * @param name
	 */
	public static  void saveBitmap(Bitmap bitmap, String name, Bitmap.CompressFormat format) {
		// 创建一个位于SD卡上的文件
		File file = new File(Environment.getExternalStorageDirectory(),
				name);
		Log.d(TAG,"saveBitmap:"+file);
		if (!file.exists()) {
			try {
				boolean falg = file.createNewFile();
				Log.d(TAG, "saveBitmap:" + falg);
			} catch (IOException e) {
				Log.d(TAG, "saveBitmap e:" + e);
				e.printStackTrace();
			}
		}
		FileOutputStream out = null;
		try{
			// 打开指定文件输出流
			out = new FileOutputStream(file);
			// 将位图输出到指定文件
			bitmap.compress(format, 100,
					out);
			out.close();
		} catch (IOException e) {
			Log.d(TAG,"saveBitmap:"+e);
			e.printStackTrace();
		}
	}
	
	
	public static void demo3() {
		//随着程序逻辑变得越来越复杂，RxJava依然能够保持简洁
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				emitter.onNext(123);
				sleep(3000);
				emitter.onNext(456);
				emitter.onComplete();
			}
		}).observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(new Consumer<Integer>() {
					@Override
					public void accept(Integer integer) throws Exception {
						Log.e(TAG,"Consumer<Integer>："+integer+"");
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						Log.e(TAG,"Consumer<Throwable>："+throwable+"");
					}
				}, new Action() {
					@Override
					public void run() throws Exception {
						Log.e(TAG,"Action：run");
					}
				});
		

	
	}
	
	
	public static void demo2() {
		//稍微进阶使用
		Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("连载1");
				emitter.onNext("连载2");
				emitter.onNext("连载3");
				Log.e(TAG,"subscribe:"+ Thread.currentThread());
				emitter.onComplete();
			}
		})
				.observeOn(AndroidSchedulers.mainThread())//回调在主线程
				.subscribeOn(Schedulers.io())//执行在io线程
				//.subscribeOn(AndroidSchedulers.mainThread())
				//.observeOn(Schedulers.io())
				.subscribe(new Observer<String>() {
					@Override
					public void onSubscribe(Disposable d) {
						Log.e(TAG,"onSubscribe");
					}
					
					@Override
					public void onNext(String value) {
						Log.e(TAG,"onNext:"+value+" "+Thread.currentThread());
						//Log.e(TAG,"onNext:"+ Thread.currentThread());
					}
					
					@Override
					public void onError(Throwable e) {
						Log.e(TAG,"onError="+e.getMessage());
					}
					
					@Override
					public void onComplete() {
						Log.e(TAG,"onComplete()");
					}
				});
		
	}
	
	public static void demo1() {
		//最简单的RxJava用法
		//被观察者
		Observable novel = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				emitter.onNext("连载1");
				emitter.onNext("连载2");
				emitter.onNext("连载3");
				
				for(int i = 0 ; i < 10 ;i ++){
					emitter.onNext("连载"+(i+4));
				}
				Log.e(TAG,"subscribe:"+ Thread.currentThread());
				emitter.onComplete();
				//onError和onComplete是互斥的，
				// Observer（观察者）只能接收到一个，
				// OnComplete可以重复调用，但是Observer（观察者）只会接收一次，
				// 而onError不可以重复调用，第二次调用就会报异常。
			}
		});
		
		//观察者
		Observer<String> reader=new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				mDisposable=d;
				Log.e(TAG,"onSubscribe");
			}
			
			@Override
			public void onNext(String value) {
				Log.e(TAG,"onNext:"+value + " "+Thread.currentThread());
				if ("2".equals(value)){
					mDisposable.dispose();
					return;
				}
				
			}
			
			@Override
			public void onError(Throwable e) {
				Log.e(TAG,"onError="+e.getMessage());
			}
			
			@Override
			public void onComplete() {
				Log.e(TAG,"onComplete()");

			}
		};
		
		//第三步：读者和连载小说建立订阅关系
		novel.subscribe(reader);//一行代码搞定
		
		
		
	}
}
