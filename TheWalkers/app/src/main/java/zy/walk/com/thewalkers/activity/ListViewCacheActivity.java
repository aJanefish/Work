package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.ListViewCacheAdapter;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;


@ViewLayout(R.layout.activity_list_view_cache)
public class ListViewCacheActivity extends AppCompatActivity {

    private static final String TAG = "ListViewCacheActivity";
    @ViewField(getId = R.id.activity_list_view_cache_list_view)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view_cache);
        ViewUtils.register(this);

    }

    ListViewCacheAdapter.ViewHolder viewHolder = null;

    @Override
    protected void onStart() {
        super.onStart();

        listView.setAdapter(new ListViewCacheAdapter(this));
        listView.setRecyclerListener(new AbsListView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {
                if (viewHolder == null) {
                    viewHolder = (ListViewCacheAdapter.ViewHolder) view.getTag();
                    Log.d(TAG, "onMovedToScrapHeap 1:" + viewHolder + " - " + viewHolder.title.getText());
                } else {
                    Log.d(TAG, "onMovedToScrapHeap 2:" + viewHolder + " - " + viewHolder.title.getText());
                }

            }
        });


    }
}
