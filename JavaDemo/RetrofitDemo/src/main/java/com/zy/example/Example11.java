package com.zy.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zy.example.entity.Blog;
import com.zy.example.entity.InfoBean;
import com.zy.example.entity.Result;
import com.zy.example.entity.SingouResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.List;

public class Example11 {

    //
    //private static final String URL = "https://mice.singou.mo/api/robot/operationRobotInfo?operation=2&robotCode=888888889&location=0/";
    private static final String URL = "https://mice.singou.mo/";

    public interface BlogService {

        @GET("")
        Call<ResponseBody> get(@Url String url);

        @GET("api/robot/operationRobotInfo?operation=2&robotCode=888888889&location=0")
        Call<ResponseBody> get();

        @GET("api/robot/operationRobotInfo?operation=2&robotCode=888888889&location=0")
        Observable<SingouResult<Object>> get1();

        @GET("api/robot/operationRobotInfo?operation=2&robotCode=888888889&location=0")
        Observable<SingouResult<InfoBean>> get2();

        @GET("api/robot/operationRobotInfo?operation=2&robotCode=888888889&location=0")
        Observable<Object> getObject();

        @GET("api/robot/operationRobotInfo")
        Observable<Object> operationRobotInfo(@Query("token") String token,
                                      @Query("operation") String operation,
                                      @Query("robotCode") String robotCode,
                                      @Query("location") String location);
    }

    public static void main(String args[]){

        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        BlogService blogService = retrofit.create(BlogService.class);

        Call<ResponseBody> responseBodyCall = blogService.get();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        //{"status":200,"info":{"robotCode":"888888889","robotName":"天機一號 (小紫)","location":0}}
        Observable<SingouResult<Object>> responseBodyCall1 = blogService.get1();
        responseBodyCall1.observeOn(Schedulers.io())
                .subscribe(new Subscriber<SingouResult<Object>>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:"+e);

            }

            @Override
            public void onNext(SingouResult<Object> singouResult) {
                System.out.println("onNext:"+singouResult);

            }
        });


        Observable<SingouResult<InfoBean>> responseBodyCall2 = blogService.get2();
        responseBodyCall2.observeOn(Schedulers.io())
                .subscribe(new Subscriber<SingouResult<InfoBean>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:"+e);

                    }

                    @Override
                    public void onNext(SingouResult<InfoBean> singouResult) {
                        System.out.println("onNext:"+singouResult);

                    }
                });



        Observable<Object> responseBodyCall3 = blogService.getObject();
        responseBodyCall3.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:"+e);

                    }

                    @Override
                    public void onNext(Object object) {
                        System.out.println("onNext:"+object.toString());

                    }
                });


        //https://mice.singou.mo/api/robot/operationRobotIn?token=odrex7vvwtwcc&operation=1&robotCode=888888885&location=
        Observable<Object> responseBodyCall4 = blogService.operationRobotInfo("odrex7vvwtwcc","1","888888885","0");
        responseBodyCall4.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:"+e);

                    }

                    @Override
                    public void onNext(Object object) {
                        System.out.println("onNext:"+object.toString());

                    }
                });
    }
}
