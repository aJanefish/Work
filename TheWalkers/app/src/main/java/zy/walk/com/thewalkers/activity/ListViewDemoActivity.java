package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.ListViewDemoAdapter;
import zy.walk.com.thewalkers.imagesandanimations.event.ListViewDemoEvent;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewDemoActivity extends AppCompatActivity {

    private ListView list_view;

    private List<ListViewDemoEvent> listViewDemoEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        initView();
    }

    private void initView() {
        listViewDemoEvents = new ArrayList<>();
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.gril,"girl"));
        listViewDemoEvents.add(new ListViewDemoEvent(R.drawable.add,"add"));




        list_view = findViewById(R.id.activity_list_view_demo_list_view);

        list_view.setAdapter(new ListViewDemoAdapter(listViewDemoEvents,this));

    }
}
