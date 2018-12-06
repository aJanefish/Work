package demo.okhttp.zy.com.okhttpdemo.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import demo.okhttp.zy.com.okhttpdemo.event.FaceEvent;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class FaceManager {

    private static String serverIp = "192.168.201.10";
    private static String SERVER_PATH = "http://" + serverIp;

    private final Context context;
    private final OkHttpClient client;
    private ExecutorService executorService = null;
    private String cookie = null;
    private static final String TAG = "FaceManager";

    public FaceManager(Context context) {
        this.context = context;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        client = builder.build();

        executorService = Executors.newCachedThreadPool();

        login("test@megvii.com", "123456");
    }


    //更新用户 PUT
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void upDateSubject(int id) {

        HashMap mapDatas = new HashMap();
        mapDatas.put("subject_type", Integer.valueOf("0"));
        mapDatas.put("name", "upDatename");

        mapDatas.put("email", "upDate641519166@qq.com");
        mapDatas.put("phone", "upDate18328582499");


        //性别{0: 未选择, 1: 男, 2: 女}
        mapDatas.put("gender", 1);

        //头像图片base64编码
        //mapDatas.put("avatar", avatar);

        //部门
        mapDatas.put("department", "upDateSingou");


        //职位
        mapDatas.put("title", "upDate程序员");


        //签名
        mapDatas.put("description", "upDate你若安好，便是晴天");

        //来访时间
        //mapDatas.put("start_time", Integer.valueOf(start_time));


        //离开时间
        //mapDatas.put("end_time", Integer.valueOf(end_time));


        //识别头像列表（通过接口2.7上传识别头像），在接口[2.5. 更新用户信息]为必填参数
        int[] photo_ids = new int[]{16791,16792};
        mapDatas.put("photo_ids", photo_ids);


        //来访目的
        //mapDatas.put("purpose", Integer.valueOf(purpose));


        //受访人
        mapDatas.put("intervieewee", "zhangyu");


        //来访单位
        mapDatas.put("come_form", "TopWise");


        //工号
        mapDatas.put("job_number", "20121414ads61316");


        //备注
        mapDatas.put("remark", "这是备注");


        //生日
        //mapDatas.put("birthday", Integer.valueOf(birthday));


        //入职时间
        //mapDatas.put("entry_date", Integer.valueOf(entry_date));


        String json = JSONObject.wrap(mapDatas).toString();
        Request request = (new Request.Builder())
                .addHeader("Cookie", cookie)
                //.post(RequestBody.create(MediaType.parse("application/json"), json))
                .put(RequestBody.create(MediaType.parse("application/json"), json))
                .url(SERVER_PATH + "/subject/"+id)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "upDateSubject:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "upDateSubject:" + response);
                Log.d(TAG, "upDateSubject:" + response.body().string());
            }
        });

    }


    //创建用户 POST
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createSubject() {

        HashMap mapDatas = new HashMap();
        mapDatas.put("subject_type", Integer.valueOf("0"));
        mapDatas.put("name", "name");

        //邮箱不能重复
        //mapDatas.put("email", "641519166@qq.com");
        mapDatas.put("phone", "18328582499");


        //性别{0: 未选择, 1: 男, 2: 女}
        mapDatas.put("gender", 1);

        //头像图片base64编码
        //mapDatas.put("avatar", avatar);

        //部门
        mapDatas.put("department", "Singou");


        //职位
        mapDatas.put("title", "程序员");


        //签名
        mapDatas.put("description", "你若安好，便是晴天");

        //来访时间
        //mapDatas.put("start_time", Integer.valueOf(start_time));


        //离开时间
        //mapDatas.put("end_time", Integer.valueOf(end_time));


        //识别头像列表（通过接口2.7上传识别头像），在接口[2.5. 更新用户信息]为必填参数
        int[] photo_ids = new int[]{16791,16792};
        mapDatas.put("photo_ids", photo_ids);


        //来访目的
        //mapDatas.put("purpose", Integer.valueOf(purpose));


        //受访人
        mapDatas.put("intervieewee", "zhangyu");


        //来访单位
        mapDatas.put("come_form", "TopWise");


        //工号
        mapDatas.put("job_number", "20121414ads61316");


        //备注
        mapDatas.put("remark", "这是备注");


        //生日
        //mapDatas.put("birthday", Integer.valueOf(birthday));


        //入职时间
        //mapDatas.put("entry_date", Integer.valueOf(entry_date));


        String json = JSONObject.wrap(mapDatas).toString();
        Request request = (new Request.Builder())
                .addHeader("Cookie", cookie)
                .post(RequestBody.create(MediaType.parse("application/json"), json))
                .url(SERVER_PATH + "/subject")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "createSubject:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "createSubject:" + response);
                Log.d(TAG, "createSubject:" + response.body().string());
            }
        });

    }


    //删除用户 DELETE
    public void deleteUser(int id) {

        Request request = (new Request.Builder())
                .addHeader("Cookie", cookie)
                .delete()
                .url(SERVER_PATH + "/subject/" + id)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "deleteUser:" + e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "deleteUser:" + response);
                Log.d(TAG, "deleteUser:" + response.body().string());
            }
        });

    }


    //获取用户信息  GET
    public void getSubjectById(final int id) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Request request = (new Request.Builder())
                        .addHeader("Cookie", cookie)
                        .url(SERVER_PATH + "/subject/" + id)
                        .get()
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "getSubjectById:" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d(TAG, "getSubjectById:" + response);
                        Log.d(TAG, "getSubjectById:" + response.body().string());
                    }
                });
            }
        });
    }


    //上传识别底库,更新人脸数据
    public void uploadSubjectPhoto(final int id) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String uri = "http://encounter-msc.singou.mo/res/upload/img/1543573017e95999e470bbaa1e5b46a5a31427f82c.jpg";
                uri = "http://encounter-msc.singou.mo///res///upload///img///1543488173401dbaca1219c85a2cea0759ca581420.jpg";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(uri)
                        .build();
                Bitmap bitmap = null;
                try {
                    //获取人脸图像
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "uploadSubjectPhoto response:" + response.body());
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (bitmap == null) {
                    Log.d(TAG, "bitmap:" + bitmap);
                    return;
                }
                //bitmap to byte[]
                ByteArrayOutputStream baops = new ByteArrayOutputStream();
                Bitmap.CompressFormat format = uri.contains("png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
                bitmap.compress(format, 80, baops);
                final byte[] data = baops.toByteArray();
                Log.d(TAG, "data.length:" + data.length);

                RequestBody image = new RequestBody() {
                    public long contentLength() throws IOException {
                        return (long) data.length;
                    }

                    public MediaType contentType() {
                        return MediaType.parse("image/png");
                    }

                    public void writeTo(BufferedSink sink) throws IOException {
                        sink.write(data);
                    }
                };


                okhttp3.MultipartBody.Builder builder = new okhttp3.MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                builder.addFormDataPart("photo", "photo.jpg", image);
                if (id != -1) {
                    builder.addFormDataPart("subject_id", id + "");
                }


                //POST
                MultipartBody body = builder.build();
                Request request1 = new Request.Builder()
                        .addHeader("Cookie", cookie)
                        .post(body)
                        .url(SERVER_PATH + "/subject/photo")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request1).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "response:" + response);
                try {
                    Log.d(TAG, "response:" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    //获取所有用户列表 需要cookie
    public void getAllSubjects() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //http://192.168.201.10/mobile-admin/subjects
                final Request request = (new Request.Builder())
                        .addHeader("Cookie", cookie)
                        .url(SERVER_PATH + "/mobile-admin/subjects")
                        .get()
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //Log.d(TAG,""+response.body().string());
                        FaceEvent faceEvent = new Gson().fromJson(response.body().string(), FaceEvent.class);
                        Log.d(TAG, "" + faceEvent.getData().size());
                    }
                });
            }
        });

    }

    public void login() {
        login("test@megvii.com", "123456");
    }

    //get Cookie
    private void login(String user_name, String pass_word) {
        JSONObject object = new JSONObject();
        try {
            object.put("username", user_name);
            object.put("password", pass_word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //http://192.168.201.10/auth/login
        String strJson = object.toString();
        Request request = new Request.Builder()
                .addHeader("user-agent", "Koala Admin")
                .post(RequestBody.create(MediaType.parse("application/json"), strJson))
                .url(SERVER_PATH + "/auth/login")
                .build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "OkHttpCallback:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "OkHttpCallback:" + response);
                String result = response.body().string();
                Log.d(TAG, "OkHttpCallback:" + result);

                cookie = response.header("Set-Cookie", cookie);
                Log.d(TAG, "OkHttpCallback cookie:" + cookie);

            }
        };
        run(request, callback);
    }

    private void run(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }


}
