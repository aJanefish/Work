package zy.walk.com.thewalkers.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static final String image = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
    private static final String web = "http://www.msc.org.mo/visit.php?cid=4&lg=cn#.W8gtifkzaUk";


    public static void test4(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("intent","ticket_price");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("location","left");
            jsonObject1.put("type","qr");
            jsonObject1.put("url","http://www.msc.org.mo/visit.php?cid=4&lg=cn#.W8gtifkzaUk");

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("location","center");
            jsonObject2.put("type","null");
            jsonObject2.put("url","null");

            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("location","right");
            jsonObject3.put("type","qr");
            jsonObject3.put("url","http://www.msc.org.mo/visit.php?cid=4&lg=cn#.W8gtifkzaUk");

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            jsonArray.put(jsonObject3);

            jsonObject.put("date",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            Log.d("JsonUtils",""+jsonObject.toString());
        }
    }

    public static void test3(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("intent","traffic_shuttle");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("location","left");
            jsonObject1.put("type","null");
            jsonObject1.put("url","null");

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("location","center");
            jsonObject2.put("type","null");
            jsonObject2.put("url","null");

            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("location","right");
            jsonObject3.put("type","qr");
            jsonObject3.put("url","http://www.msc.org.mo/visit.php?cid=3&lg=cn#.W8gNbegzY2w");

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            jsonArray.put(jsonObject3);

            jsonObject.put("date",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            Log.d("JsonUtils",""+jsonObject.toString());
        }
    }

    public static void test2(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("intent","traffic_parkPrice");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("location","left");
            jsonObject1.put("type","null");
            jsonObject1.put("url","null");

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("location","center");
            jsonObject2.put("type","qr");
            jsonObject2.put("url","http://www.msc.org.mo/visit.php?cid=3&lg=cn#.W8grYPkzaUl");

            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("location","right");
            jsonObject3.put("type","null");
            jsonObject3.put("url","null");

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            jsonArray.put(jsonObject3);

            jsonObject.put("date",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            Log.d("JsonUtils",""+jsonObject.toString());
        }
    }

    public static void test1(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("intent","special_attention");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("location","left");
            jsonObject1.put("type","qr");
            jsonObject1.put("url","http://www.msc.org.mo/visit.php?cid=9&lg=cn#.W8VsdOgzY2w");

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("location","center");
            jsonObject2.put("type","null");
            jsonObject2.put("url","null");

            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("location","right");
            jsonObject3.put("type","null");
            jsonObject3.put("url","null");

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            jsonArray.put(jsonObject3);

            jsonObject.put("date",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            Log.d("JsonUtils",""+jsonObject.toString());
        }
    }



}
