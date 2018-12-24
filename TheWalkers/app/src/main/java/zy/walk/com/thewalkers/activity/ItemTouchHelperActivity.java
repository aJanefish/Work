package zy.walk.com.thewalkers.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemTouchHelperActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private String TAG = "ItemTouchHelperActivity";
    private ArrayList<String> mlist;
    private CommonAdapter<String> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch_helper);
        initView();
        initData();
        initDrag();
    }

    private void initDrag() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT,
                ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position

                //使用集合工具类Collections，分别把中间所有的item的位置重新交换
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mlist, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mlist, i, i - 1);
                    }
                }
                //通知Adapter更新状态
                commonAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;


            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mlist.remove(position);//侧滑删除数据
                commonAdapter.notifyItemRemoved(position); //通知Adapter更新状态
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//                    viewHolder.itemView.setAlpha(alpha);
//                    viewHolder.itemView.setTranslationX(dX);
//                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recycler_view);
    }

    @SuppressLint("WrongConstant")
    private void initData() {
        mlist = new ArrayList<>();
        int tmp = 20;
        for (int i = 0; i < tmp; i++) {
            mlist.add("Touch"+i);
        }

        commonAdapter = new CommonAdapter<String>(this,R.layout.activity_item_touch_helper_item,mlist) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.activity_item_touch_helper_item_title,s);
            }
        };

        //recycler_view.setLayoutManager(new LinearLayoutManager(this));

//        @SuppressLint("WrongConstant") GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
//        recycler_view.setLayoutManager(gridLayoutManager1);

        recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        recycler_view.setAdapter(commonAdapter);


    }

    private void initView() {
        recycler_view = findViewById(R.id.activity_item_touch_helper_recycler_view);
    }
}
