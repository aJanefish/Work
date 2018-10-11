package zy.retrotif.review.com.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APi {
    // @GET注解的作用:采用Get方法发送网络请求
    // getNews(...) = 接收网络请求数据的方法
    // 其中返回类型为Call<News>，News是接收数据的类（即上面定义的News类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("word/word")
    Call<News> getNews(@Query("num") String num, @Query("page")String page);
}

//public interface APi {
//
//    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
//    @GET("word/word")
//    Call<News> getNews(@Query("num") String num,@Query("page")String page);
//}


