package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zy.walk.com.thewalkers.adapter.ShowMessageAdapter;
import zy.walk.com.thewalkers.event.AuxiliaryItemBean;
import zy.walk.com.thewalkers.event.ShowBeanBate;
import zy.walk.com.thewalkers.newwork.OkhttpUtils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuxiliaryInfoShowActivity extends AppCompatActivity {

    private RecyclerView show_recycler_view;
    private String TAG = "AuxiliaryInfoShowActivity";
    private List<ShowBeanBate.ContentBean> contentBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxiliary_info_show);
        initView();
        initData();
    }



    public void upDateShowBean() {

        Observable.create(new ObservableOnSubscribe<ShowBeanBate>() {
            @Override
            public void subscribe(final ObservableEmitter<ShowBeanBate> emitter) throws Exception {

                OkhttpUtils.getAuxiliaryAll(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String date = response.body().string();
                        try{
                            ShowBeanBate showBeanBate = new Gson().fromJson(date, ShowBeanBate.class);
                            emitter.onNext(showBeanBate);
                        }catch (Exception e){
                            Log.d(TAG,"upDateShowBean:"+e);
                            emitter.onError(new Throwable(e));
                        }finally {
                            emitter.onComplete();
                        }
                    }
                });
                Log.e(TAG, "subscribe:" + Thread.currentThread());
            }
        }).observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                //.subscribeOn(AndroidSchedulers.mainThread())
                //.observeOn(Schedulers.io())
                .subscribe(new Observer<ShowBeanBate>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(ShowBeanBate showBeanBate) {
                        contentBeanList = showBeanBate.getContent();
                        show_recycler_view.setAdapter(new ShowMessageAdapter(contentBeanList));
                        List<String> httpList = new ArrayList<String>();
                        for (ShowBeanBate.ContentBean contentBean : contentBeanList) {
                            httpList.add(contentBean.getIntention_name());
                            AuxiliaryItemBean auxiliaryItemBean = new Gson().fromJson(contentBean.getAuxiliary_info(), AuxiliaryItemBean.class);
                            for (AuxiliaryItemBean.EnBean enBean : auxiliaryItemBean.getEn()) {
                                if(enBean.getUrl().contains("http")){
                                    httpList.add(enBean.getUrl());
                                }
                            }
                            for (AuxiliaryItemBean.PtBean ptBean : auxiliaryItemBean.getPt()) {
                                if(ptBean.getUrl().contains("http")){
                                    httpList.add(ptBean.getUrl());
                                }
                            }
                            for (AuxiliaryItemBean.ZhBean zhBean : auxiliaryItemBean.getZh()) {
                                if(zhBean.getUrl().contains("http")){
                                    httpList.add(zhBean.getUrl());
                                }
                            }

                        }
                        Log.d(TAG,"httpList.size:"+httpList.size());
                        int size = httpList.size();
                        for (int i = 0; i < size; i++) {
                            Log.d(TAG,i+" : "+httpList.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError=" + e.getMessage());
                        Toast.makeText(AuxiliaryInfoShowActivity.this, "更新失败:" + e, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete()");

                        Toast.makeText(AuxiliaryInfoShowActivity.this, "更新成功", Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void initData() {
        upDateShowBean();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        show_recycler_view = findViewById(R.id.activity_auxiliary_info_show_recycler_view);
        show_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void back(View view) {
        finish();
    }

}
