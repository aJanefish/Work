package com.http.chain;

import com.http.Request;
import com.http.Response;

import java.io.IOException;
import java.util.Random;

//请求网络数据
public class CallServerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /**写入请求行
         * 写入请求头
         *
         * 读取相应行
         * 读取响应头
         *
         * */

        Request request = chain.request();
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(10)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Response response = new Response(request,"CallServer"+System.currentTimeMillis());
        return response;
    }
}
