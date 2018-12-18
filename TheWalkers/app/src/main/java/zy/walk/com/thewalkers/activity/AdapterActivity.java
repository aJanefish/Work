package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.DemoAdapter;
import zy.walk.com.thewalkers.imagesandanimations.event.MessageEvent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zy.myadapter.BaseAdapter;
import com.zy.myadapter.BaseHolder;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {

    private RecyclerView activity_adapter_recycler_view;
    private String TAG = "AdapterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        initView();
    }


    private void initView() {
        activity_adapter_recycler_view = findViewById(R.id.activity_adapter_recycler_view);

    }

    @SuppressLint("WrongConstant")
    public void demo0(View view) {

        switch (view.getId()){
            case R.id.activity_adapter_button1:

                //用法(在Activity里初始化控件后)：
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                activity_adapter_recycler_view.setLayoutManager(layoutManager);
                break;
            case R.id.activity_adapter_button2:
                //用法(在Activity里初始化控件后)：
                // 第二个参数就是用于指定方向是竖直还是水平，第三个参数用于指定是否从右到左布局，基本都是false，我们的习惯都是左到右的排列方式
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
                layoutManager1.setOrientation(RecyclerView.VERTICAL);
                activity_adapter_recycler_view.setLayoutManager(layoutManager1);

                break;
            case R.id.activity_adapter_button3:
                //用法(在Activity里初始化控件后)：
                // 第二个参数就是用于指定方向是竖直还是水平，第三个参数用于指定是否从右到左布局，基本都是false，我们的习惯都是左到右的排列方式
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                activity_adapter_recycler_view.setLayoutManager(layoutManager2);
                break;
            case R.id.activity_adapter_button4:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false);
                activity_adapter_recycler_view.setLayoutManager(gridLayoutManager);
                break;
            case R.id.activity_adapter_button5:
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
                activity_adapter_recycler_view.setLayoutManager(gridLayoutManager1);
                break;
            case R.id.activity_adapter_button6:
                GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 2);
                gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        //以下代码仅为上图示例为写，具体场景中应该根据需求具体编写
//                        if (position == 3) {
//                            return 2;
//                        }
//                        if (position == 7) {
//                            return 3;
//                        }
                        return 2;


                    }
                });
                gridLayoutManager2.setSpanCount(8);
                activity_adapter_recycler_view.setLayoutManager(gridLayoutManager2);
                break;

        }



        List<String> mTmpDates = new ArrayList<>();
        mTmpDates.add("demo0 A");
        mTmpDates.add("demo0 B");
        mTmpDates.add("demo0 C");
        mTmpDates.add("demo0 D");
        mTmpDates.add("demo0 E");
        mTmpDates.add("demo0 F");
        mTmpDates.add("demo0 G");
        mTmpDates.add("demo0 H");
        mTmpDates.add("demo0 I");
        mTmpDates.add("demo0 J");
        mTmpDates.add("demo0 K");
        mTmpDates.add("demo0 L");


        //activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));



        DemoAdapter demoAdapter = new DemoAdapter(mTmpDates);
        activity_adapter_recycler_view.setAdapter(demoAdapter);
    }



    @SuppressLint("WrongConstant")
    public void demo1(View view) {
        List<String> mTmpDates = new ArrayList<>();
        mTmpDates.add("A");
        mTmpDates.add("B");
        mTmpDates.add("C");
        mTmpDates.add("D");
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        activity_adapter_recycler_view.setAdapter(new CommonAdapter<String>(this, R.layout.activity_adapter_item_list, mTmpDates) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.activity_adapter_item_list_title, s);
            }
        });
    }

    public void demo2(View view) {
        List<MessageEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new MessageEvent("Aa",MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Bb",MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Cc",MessageEvent.RIGHT));
        mTmpDates.add(new MessageEvent("Dd",MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Ee",MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Ff",MessageEvent.RIGHT));
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        MultiItemTypeAdapter<MessageEvent> multiItemTypeAdapter = new MultiItemTypeAdapter<>(this,mTmpDates);
        multiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<MessageEvent>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.activity_adapter_item_list_left;
            }

            @Override
            public boolean isForViewType(MessageEvent item, int position) {
                return item.type == MessageEvent.LEFT;
            }

            @Override
            public void convert(ViewHolder holder, MessageEvent messageEvent, int position) {
                holder.setText(R.id.activity_adapter_item_list_left_title,messageEvent.title);
            }
        });

        multiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<MessageEvent>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.activity_adapter_item_list_center;
            }

            @Override
            public boolean isForViewType(MessageEvent item, int position) {
                return item.type == MessageEvent.CENTER;
            }

            @Override
            public void convert(ViewHolder holder, MessageEvent messageEvent, int position) {
                holder.setText(R.id.activity_adapter_item_list_center_title,messageEvent.title);
            }
        });


        multiItemTypeAdapter.addItemViewDelegate(new ItemViewDelegate<MessageEvent>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.activity_adapter_item_list_right;
            }

            @Override
            public boolean isForViewType(MessageEvent item, int position) {
                return item.type == MessageEvent.RIGHT;
            }

            @Override
            public void convert(ViewHolder holder, MessageEvent messageEvent, int position) {
                holder.setText(R.id.activity_adapter_item_list_right_title,messageEvent.title);
            }
        });

        activity_adapter_recycler_view.setAdapter(multiItemTypeAdapter);

    }


    public void demo01(View view) {
        List<MessageEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new MessageEvent("Aa",true));
        mTmpDates.add(new MessageEvent("Bb",false));
        mTmpDates.add(new MessageEvent("Cc",true));
        mTmpDates.add(new MessageEvent("Dd",false));
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter<MessageEvent> baseAdapter = new BaseAdapter<>(this,mTmpDates);
        baseAdapter.addDelegate(new com.zy.myadapter.ItemViewDelegate<MessageEvent>() {
            @Override
            public int getLayoutId() {
                return R.layout.activity_adapter_item_list_left;
            }

            @Override
            public boolean isShowing(MessageEvent messageEvent, int position) {
                return true;
            }

            @Override
            public void convert(BaseHolder holder, MessageEvent messageEvent, int position) {
                holder.setText(R.id.activity_adapter_item_list_left_title,messageEvent.title);
            }
        });
        activity_adapter_recycler_view.setAdapter(baseAdapter);
    }
}
