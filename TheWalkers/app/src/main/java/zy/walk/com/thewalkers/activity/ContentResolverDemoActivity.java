package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ContentResolverDemoActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private TextView des;
    private Uri uri_user;
    private String TAG = "ContentResolverDemo";
    private Uri uri_job;
    private Uri uri_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver_demo);
        initContentResolver();
        initView();
    }

    private void initContentResolver() {
        // 设置URI
        // Uri 需要被注册才能被识别到
        uri_test = Uri.parse("content://com.zy.content.provider.demo/zy/test");
        uri_job = Uri.parse("content://com.zy.content.provider.demo/job");
        uri_user = Uri.parse("content://com.zy.content.provider.demo/user");

    }


    @SuppressLint("WrongConstant")
    private void initView() {
        des = findViewById(R.id.activity_content_resolver_demo_des);
        recycler_view = findViewById(R.id.activity_content_resolver_demo_recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        final List<ContentResolverEvent> list = new ArrayList<>();
        list.add(new ContentResolverEvent(ContentResolverEvent.USER, ContentResolverEvent.QUERY, "user query", uri_user));
        list.add(new ContentResolverEvent(ContentResolverEvent.USER, ContentResolverEvent.DELETE, "user Delete", uri_user));
        list.add(new ContentResolverEvent(ContentResolverEvent.USER, ContentResolverEvent.INSERT, "user Insert", uri_user));
        list.add(new ContentResolverEvent(ContentResolverEvent.USER, ContentResolverEvent.UPDATE, "user Update", uri_user));

        list.add(new ContentResolverEvent(ContentResolverEvent.JOB, ContentResolverEvent.QUERY, "job query", uri_job));
        list.add(new ContentResolverEvent(ContentResolverEvent.JOB, ContentResolverEvent.DELETE, "job Delete", uri_job));
        list.add(new ContentResolverEvent(ContentResolverEvent.JOB, ContentResolverEvent.INSERT, "job Insert", uri_job));
        list.add(new ContentResolverEvent(ContentResolverEvent.JOB, ContentResolverEvent.UPDATE, "job Update", uri_job));

        list.add(new ContentResolverEvent(ContentResolverEvent.TEST, ContentResolverEvent.QUERY, "test query", uri_test));
        list.add(new ContentResolverEvent(ContentResolverEvent.TEST, ContentResolverEvent.DELETE, "test Delete", uri_test));
        list.add(new ContentResolverEvent(ContentResolverEvent.TEST, ContentResolverEvent.INSERT, "test Insert", uri_test));
        list.add(new ContentResolverEvent(ContentResolverEvent.TEST, ContentResolverEvent.UPDATE, "test Update", uri_test));


        final CommonAdapter<ContentResolverEvent> commonAdapter = new CommonAdapter<ContentResolverEvent>(this, R.layout.activity_content_resolver_demo_item, list) {
            @Override
            protected void convert(ViewHolder holder, ContentResolverEvent contentResolverEvent, int position) {
                holder.setText(R.id.activity_content_resolver_demo_item_title, contentResolverEvent.name);
            }
        };

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int index) {
                ContentResolverEvent contentResolverEvent = list.get(index);
                switch (contentResolverEvent.typeId) {
                    case ContentResolverEvent.QUERY:
                        query(contentResolverEvent.uri);
                        break;
                    case ContentResolverEvent.INSERT:
                        insert(contentResolverEvent.uri);
                        break;
                    case ContentResolverEvent.DELETE:
                        delete(contentResolverEvent.uri);
                        break;
                    case ContentResolverEvent.UPDATE:
                        update(contentResolverEvent.uri);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });

        recycler_view.setAdapter(commonAdapter);
    }

    private void update(Uri uri) {
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "ZhangYu");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE\n");
        stringBuilder.append(values + "\n");
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        try {
            //resolver.update()
            String[] strings = new String[]{
                    "selectionArgs1", "selectionArgs2", "selectionArgs3"
            };
            int type = resolver.update(uri, values, "where", strings);
            stringBuilder.append("type:" + type + "\n");
        } catch (Exception e) {
            //Log.d(TAG,"insert:"+e);
            stringBuilder.append("update:" + e + "\n");
        } finally {
            //Log.d(TAG,"insert:"+values);

            //Log.d(TAG,""+uri);
            des.setText(stringBuilder);
        }
    }


    private void delete(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE\n");
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        try {
            String[] strings = new String[]{
                    "selectionArgs1", "selectionArgs2", "selectionArgs3"
            };
            int type = resolver.delete(uri, "where", strings);
            stringBuilder.append("type:" + type + "\n");
        } catch (Exception e) {
            //Log.d(TAG,"insert:"+e);
            stringBuilder.append("delete:" + e + "\n");
        } finally {
            //Log.d(TAG,"insert:"+values);

            //Log.d(TAG,""+uri);
            des.setText(stringBuilder);
        }
    }

    private void insert(Uri uri) {
        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "ZhangYu");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT\n");
        stringBuilder.append(values + "\n");
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        Uri mUri = null;
        try {

            mUri = resolver.insert(uri, values);
        } catch (Exception e) {
            //Log.d(TAG,"insert:"+e);
            stringBuilder.append("insert:" + e + "\n");
        } finally {
            //Log.d(TAG,"insert:"+values);
            stringBuilder.append("uri:" + mUri + "\n");
            //Log.d(TAG,""+uri);
            des.setText(stringBuilder);
        }

    }

    private void query(Uri uri) {
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "ZhangYu");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("QUERY\n");
        stringBuilder.append(values + "\n");
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        try {
            String[] strings = new String[]{
                    "selectionArgs1", "selectionArgs2", "selectionArgs3"
            };
            Cursor cursor = resolver.query(uri, strings, "selection", strings, "sortOrder");
            stringBuilder.append("cursor:" + cursor + "\n");

        } catch (Exception e) {
            //Log.d(TAG,"insert:"+e);
            stringBuilder.append("insert:" + e + "\n");
        } finally {
            //Log.d(TAG,"insert:"+values);
            //Log.d(TAG,""+uri);
            des.setText(stringBuilder);
        }
    }


    class ContentResolverEvent {
        public static final int JOB = 2;
        public static final int USER = 1;
        public static final int TEST = 3;

        public static final int QUERY = 1;
        public static final int INSERT = 2;
        public static final int DELETE = 3;
        public static final int UPDATE = 4;
        Uri uri;
        int id; // 1 2 3
        int typeId; // 1 2 3 4
        String name;

        public ContentResolverEvent(int id, int typeId, String name, Uri uri) {
            this.id = id;
            this.typeId = typeId;
            this.name = name;
            this.uri = uri;
        }


    }
}
