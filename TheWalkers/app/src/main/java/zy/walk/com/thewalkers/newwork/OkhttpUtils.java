package zy.walk.com.thewalkers.newwork;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zy.walk.com.thewalkers.event.EncounterFaceEvent;

public class OkhttpUtils {
    private static final OkHttpClient okHttpClient;
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static String TAG = "OkhttpUtils";

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    public static void getAuxiliaryAll(Callback callback){

        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/getAuxiliaryAll")
                //.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        okHttpClient.newCall(request).enqueue(callback);

    }

    public static void getFace(){
        JSONObject object = new JSONObject();

        //"test@megvii.com", "123456"

        try {
            object.put("username", "test@megvii.com");
            object.put("password", "123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String serverIp = "192.168.201.10";
        final String SERVER_PATH = "http://" + serverIp;
        String strJson = object.toString();
        ///http://192.168.201.10/static/upload/avatar/2018-11-29/63b2ce43069672b0bee475b8bff5a6d76aba41cd.jpg
        Request request = (new Request.Builder())
                .addHeader("user-agent", "Koala Admin")
                .post(RequestBody.create(MediaType.parse("application/json"), strJson))
                .url(SERVER_PATH + "/auth/login")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,"response:"+response);
                String cookie = response.header("Set-Cookie");
                getFaceList(cookie,SERVER_PATH+"/mobile-admin/subjects");
            }
        });
    }

    private static void getFaceList(String cookie,String url){
        Request request = (new Request.Builder())
                .addHeader("Cookie", cookie)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,"response:"+response);
                Log.d(TAG,"response:"+response.body());
                Log.d(TAG,"response:"+response.body().string());


            }
        });

    }



    //http://encounter-msc.singou.mo/api/tool/face?method=3&username=admin&password=mSc!2016
    public static void getCookie(){
        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/face?method=3&username=admin&password=mSc!2016")
                //.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils",""+response);
                Log.d("OkhttpUtils",""+response.body());
                Log.d("OkhttpUtils","ss:"+response.body().string());

                Log.d("OkhttpUtils","headers:"+response.headers());
                String cookie = response.header("Set-Cookie");


                Log.d("OkhttpUtils","cookie:"+cookie);
                //http://encounter-msc.singou.mo/api/tool/face

                getFace(cookie);

            }
        });
    }


    public static void getFace(String cookie){


        JSONObject body = new JSONObject();
        try {

            body.put("method", "1");


        } catch (Exception e) {
            Log.d(TAG, "Exception:" + e);
        }

        Log.d(TAG, "" + body.toString());

        RequestBody requestBody = RequestBody.create(JSON, body.toString());


        Request request = new Request.Builder()
                .addHeader("Cookie", cookie)
                .url("http://encounter-msc.singou.mo/api/tool/face?method=1")
                //.post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils","1 "+response);
                Log.d("OkhttpUtils","1 "+response.body());
                Log.d("OkhttpUtils","1 ss:"+response.body().string());


                //Log.d("OkhttpUtils","1 headers:"+response.headers());

            }
        });
    }

    //获取所有encounter 的face主机ID
    public static void getRobotCode(){

        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/face?method=4")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkhttpUtils",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("OkhttpUtils",""+response);
                Log.d("OkhttpUtils",""+response.body());

                String content = response.body().string();
                new Gson().fromJson(content,EncounterFaceEvent.class);
            }
        });

    }



    public static void sendFaceError(){

        //


        JSONObject contetn = new JSONObject();
        try {
            contetn.put("robot_id", "15032047895680730");
            contetn.put("guest_id", "138");
            contetn.put("error_code", "-1000");
            contetn.put("error_content", "未知错误");
        } catch (Exception e) {
            Log.d(TAG, "Exception:" + e);
        }


        JSONArray jsonArray = new JSONArray();
        jsonArray.put(contetn);
        jsonArray.put(contetn);
        jsonArray.put(contetn);


        //[
        //    {
        //        "robot_id":"15032047895680730",
        //        "guest_id":"138",
        //        "error_code":"-1000",
        //        "error_content":"未知错误"
        //    },
        //    {
        //        "robot_id":"15032047895680730",
        //        "guest_id":"139",
        //        "error_code":"-1000",
        //        "error_content":"未知错误"
        //    }
        //]

        JSONObject body = new JSONObject();
        try {

            body.put("data",""+jsonArray.toString());
        } catch (Exception e) {
            Log.d(TAG, "Exception:" + e);
        }

        Log.d(TAG, "" + body.toString());

        RequestBody requestBody = RequestBody.create(JSON, body.toString());


        Request request = new Request.Builder()
                //.addHeader("Cookie", cookie)
                .url("http://encounter-msc.singou.mo/api/tool/recodeBindFail?data="+body.toString())
                //.post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils"," "+response);
                Log.d("OkhttpUtils"," "+response.body());
                Log.d("OkhttpUtils"," "+response.body().string());


                //Log.d("OkhttpUtils","1 headers:"+response.headers());

            }
        });
    }


    public static void getTestSingou(){


        Request request = new Request.Builder()
                .url("http://encounter-msc.singou.mo/api/tool/RecordClicks?question_name=科學館幾點鐘開門")
                //.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkhttpUtils",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkhttpUtils",""+response);
                Log.d("OkhttpUtils",""+response.body());
                Log.d("OkhttpUtils","ss:"+response.body().string());
                //Log.d("OkhttpUtils",""+new String(response.body().bytes()));
            }
        });

    }




}
