package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.BaseViewAdapter;
import zy.walk.com.thewalkers.event.BaseViewEvent;
import zy.walk.com.thewalkers.view.BaseView;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

public class ViewBaseActivity extends AppCompatActivity {

    private Random random = new Random();

    @ViewField(R.id.activity_view_base_tips)
    BaseView baseView;


    @ViewField(R.id.activity_view_base_message)
    TextView message;

    @ViewField(R.id.activity_view_base_list_view)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_base);
        ViewUtils.register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initEvent();
    }

    private void initEvent() {
        List<BaseViewEvent> list = new ArrayList<>();
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.Alpha, "设置该组件的透明度"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.Background, "设置该组件的背景颜色"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.Clickable, "设置该组件是否可以激发单击事件"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.ContentDescription, "设置该组件的主要描述信息"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.Padding, "在组件的四边设置填充区域"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.Rotation, "设置该组件的旋转角度"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.RotationX, "设置该组件绕X轴旋转的角度"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.RotationX, "设置该组件绕X轴旋转的角度"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.ScaleX, "设置该组件水平方向的缩放比"));
        list.add(new BaseViewEvent(BaseViewEvent.ViewEvent.ScaleY, "设置该组件垂直方向的缩放比"));


        final BaseViewAdapter baseViewAdapter = new BaseViewAdapter(list, this);
        listView.setAdapter(baseViewAdapter);
        baseViewAdapter.setBaseViewAdapterListener(new BaseViewAdapter.BaseViewAdapterListener() {
            @Override
            public void onEvent(BaseViewEvent baseViewEvent) {
                switch (baseViewEvent.getEvent()) {
                    case ScaleX:
                        baseView.setScaleX(random.nextFloat() * 2);
                        break;
                    case ScaleY:
                        baseView.setScaleY(random.nextFloat() * 2);
                        break;
                    case RotationX:
                        baseView.setRotationX(random.nextFloat() * 360);
                        break;
                    case RotationY:
                        baseView.setRotationY(random.nextFloat() * 360);
                        break;
                    case Rotation:
                        baseView.setRotation(random.nextFloat() * 360);
                        break;
                    case Alpha:
                        baseView.setAlpha(random.nextFloat());
                        break;
                    case Background:
                        baseView.setBackgroundColor(Color.BLUE);
                        break;
                    case Clickable:
                        baseView.setClickable(!baseView.isClickable());
                        break;
                    case ContentDescription:
                        baseView.setContentDescription("Test Content Description " + random.nextInt(100));
                        break;
                    case Padding:
                        baseView.setPadding(random.nextInt(20), random.nextInt(20), random.nextInt(20), random.nextInt(20));
                        break;
                }
                showViewDetails();
            }
        });

        baseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText("" + baseView.isClickable());
            }
        });

    }


    private void showViewDetails() {
        StringBuilder msg = new StringBuilder();
        msg.append(baseView.getLayoutParams() + "\n");
        msg.append("[width,height] - [" + baseView.getLayoutParams().width + "," + baseView.getLayoutParams().height + "]\n");
        msg.append("[Alpha] - [" + baseView.getAlpha() + "]\n");
        msg.append("[Background] - [" + baseView.getBackground() + "]\n");
        msg.append("[Clickable] - [" + baseView.isClickable() + "]\n");
        msg.append("[ContentDescription] - [" + baseView.getContentDescription() + "]\n");
        msg.append("[Left,Top,Right,Bottom] - [" + baseView.getPaddingLeft() + "," + baseView.getPaddingTop() + "," + baseView.getPaddingRight() + "," + baseView.getPaddingBottom() + "]\n");
        msg.append("[Rotation,RotationX,RotationY] - [" + baseView.getRotation() + "," + baseView.getRotationX() + "," + baseView.getRotationY() + "]\n");
        msg.append("[ScaleX,ScaleY] - [" + baseView.getScaleX() + "," + baseView.getScaleY() + "]\n");


        message.setText(msg.toString());
    }


}
