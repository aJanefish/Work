package zy.walk.com.thewalkers.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.DemoAdapter;
import zy.walk.com.thewalkers.animator.MyAnimator;
import zy.walk.com.thewalkers.imagesandanimations.event.DemoEvent;
import zy.walk.com.thewalkers.imagesandanimations.event.MessageEvent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zy.myadapter.BaseAdapter;
import com.zy.myadapter.BaseHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        switch (view.getId()) {
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


        List<DemoEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new DemoEvent("demo0 A"));
        mTmpDates.add(new DemoEvent("demo0 B"));
        mTmpDates.add(new DemoEvent("demo0 C"));
        mTmpDates.add(new DemoEvent("demo0 D"));
        mTmpDates.add(new DemoEvent("demo0 E"));
        mTmpDates.add(new DemoEvent("demo0 F"));
        mTmpDates.add(new DemoEvent("demo0 G"));
        mTmpDates.add(new DemoEvent("demo0 H"));
        mTmpDates.add(new DemoEvent("demo0 I"));
        mTmpDates.add(new DemoEvent("demo0 J"));
        mTmpDates.add(new DemoEvent("demo0 K"));
        mTmpDates.add(new DemoEvent("demo0 L"));
        mTmpDates.add(new DemoEvent("demo0 R"));
        mTmpDates.add(new DemoEvent("demo0 T"));
        mTmpDates.add(new DemoEvent("demo0 Y"));
        mTmpDates.add(new DemoEvent("demo0 S"));


        //activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        DemoAdapter demoAdapter = new DemoAdapter(mTmpDates);

        activity_adapter_recycler_view.setAdapter(demoAdapter);


        /**
         * Add an {@link RecyclerView.OnItemTouchListener} to intercept touch events before they are dispatched
         * to child views or this view's standard scrolling behavior.
         * */
        activity_adapter_recycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                boolean isIntercept = false;
                //Log.d(DemoAdapter.TAG,"onInterceptTouchEvent:"+isIntercept  + " , "+e.getAction());
                return isIntercept;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        MyAnimator myAnimator = new MyAnimator();
        int dur = 1000;
        myAnimator.setAddDuration(dur);
        myAnimator.setRemoveDuration(dur);
        myAnimator.setChangeDuration(dur);
        myAnimator.setMoveDuration(dur);
        activity_adapter_recycler_view.setItemAnimator(myAnimator);


        activity_adapter_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//：停止滑动时的状态
                    //The RecyclerView is not currently scrolling.
                    Log.d(DemoAdapter.TAG, "onScrollStateChanged SCROLL_STATE_IDLE");
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//SCROLL_STATE_DRAGGING：手指拖动时的状态
                    //The RecyclerView is currently being dragged by outside input such as user touch input.
                    Log.d(DemoAdapter.TAG, "onScrollStateChanged SCROLL_STATE_DRAGGING");
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {//SCROLL_STATE_SETTLING 惯性滑动时的状态（这是我的理解）
                    //The RecyclerView is currently animating to a final position while not under outside control.
                    Log.d(DemoAdapter.TAG, "onScrollStateChanged SCROLL_STATE_SETTLING");
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.d(DemoAdapter.TAG, "onScrolled:dx:" + dx + " ,dy:" + dy);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
        mTmpDates.add(new MessageEvent("Aa", MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Bb", MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Cc", MessageEvent.RIGHT));
        mTmpDates.add(new MessageEvent("Dd", MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Ee", MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Ff", MessageEvent.RIGHT));
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        MultiItemTypeAdapter<MessageEvent> multiItemTypeAdapter = new MultiItemTypeAdapter<>(this, mTmpDates);
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
                holder.setText(R.id.activity_adapter_item_list_left_title, messageEvent.title);
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
                holder.setText(R.id.activity_adapter_item_list_center_title, messageEvent.title);
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
                holder.setText(R.id.activity_adapter_item_list_right_title, messageEvent.title);
            }
        });

        activity_adapter_recycler_view.setAdapter(multiItemTypeAdapter);


    }


    public void demo01(View view) {
        List<MessageEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new MessageEvent("Aa", true));
        mTmpDates.add(new MessageEvent("Bb", false));
        mTmpDates.add(new MessageEvent("Cc", true));
        mTmpDates.add(new MessageEvent("Dd", false));
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter<MessageEvent> baseAdapter = new BaseAdapter<>(this, mTmpDates);
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
                holder.setText(R.id.activity_adapter_item_list_left_title, messageEvent.title);
            }
        });
        activity_adapter_recycler_view.setAdapter(baseAdapter);
    }

    /**
     * Adapter.notifyItemInserted(int position)
     * Adapter.notifyItemRemoved(int position)
     * Adapter.notifyItemChanged(int position)
     * Adapter.notifyItemMoved(int fromPosition, int  toPosition)
     * <p>
     * 另外，只有通过 notifyItemXXX() 方式更新数据源时才会触发动画行为，如果是通过 notifyDataSetChange() 方式，则不会触发动画。
     */

    public void remove(View view) {
        RecyclerView.Adapter adapter = activity_adapter_recycler_view.getAdapter();
        if (adapter instanceof DemoAdapter) {
            List<DemoEvent> list = ((DemoAdapter) adapter).getList();
            list.remove(1);
            adapter.notifyItemRemoved(1);
        }
    }

    public void add(View view) {
        RecyclerView.Adapter adapter = activity_adapter_recycler_view.getAdapter();
        if (adapter instanceof DemoAdapter) {
            List<DemoEvent> list = ((DemoAdapter) adapter).getList();
            list.add(1, new DemoEvent("add 0"));
            adapter.notifyItemInserted(1);
        }
    }

    public void change(View view) {
        RecyclerView.Adapter adapter = activity_adapter_recycler_view.getAdapter();
        if (adapter instanceof DemoAdapter) {
            List<DemoEvent> list = ((DemoAdapter) adapter).getList();
            DemoEvent demoEvent = list.get(1);
            demoEvent.setTitle("change " + new Random().nextInt(10));
            adapter.notifyItemChanged(1);
        }
    }

    public void move(View view) {
        RecyclerView.Adapter adapter = activity_adapter_recycler_view.getAdapter();
        if (adapter instanceof DemoAdapter) {
            adapter.notifyItemMoved(1, 3);
        }
    }

    public void LoadMore(View view) {

        final List<MessageEvent> mTmpDates = new ArrayList<>();
        mTmpDates.add(new MessageEvent("Aa", MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Bb", MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Cc", MessageEvent.RIGHT));
        mTmpDates.add(new MessageEvent("Dd", MessageEvent.LEFT));
        mTmpDates.add(new MessageEvent("Ee", MessageEvent.CENTER));
        mTmpDates.add(new MessageEvent("Ff", MessageEvent.RIGHT));
        activity_adapter_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        MultiItemTypeAdapter<MessageEvent> multiItemTypeAdapter = new MultiItemTypeAdapter<>(this, mTmpDates);
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
                holder.setText(R.id.activity_adapter_item_list_left_title, messageEvent.title);
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
                holder.setText(R.id.activity_adapter_item_list_center_title, messageEvent.title);
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
                holder.setText(R.id.activity_adapter_item_list_right_title, messageEvent.title);
            }
        });

        final LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(multiItemTypeAdapter);
        mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_loading, activity_adapter_recycler_view, false));
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.d(TAG, "onLoadMoreRequested");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean coming = Math.random() > 0.5;
                        MessageEvent msg = null;
                        msg = new MessageEvent(coming ? "人马" : "xiaohei", coming ? MessageEvent.CENTER : MessageEvent.LEFT);
                        mTmpDates.add(msg);
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

        multiItemTypeAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(AdapterActivity.this, "Click:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(AdapterActivity.this, "LongClick:" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        activity_adapter_recycler_view.setAdapter(mLoadMoreWrapper);


    }
}
