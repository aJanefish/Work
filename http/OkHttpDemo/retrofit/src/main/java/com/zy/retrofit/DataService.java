package com.zy.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DataService {
    //指定get请求方式  指定路径 有时候路径除了baseUrl还有一部分比如 http://write.blog.csdn.net/mdeditor
    //http://write.blog.csdn.net/ 一般是baseUrl
    //而 mdeditor是相对路径的
    @GET
    Call<String> baiDu(@Url String url);
}
