package zy.walk.com.thewalkers.assign;

import android.content.Intent;

import okhttp3.Request;
import zy.walk.com.thewalkers.MainActivity;
import zy.walk.com.thewalkers.imagesandanimations.event.MainEvent;

public class AssignTest {
    public static void test() {


        DefaultAssignIntent.Builder builder = new DefaultAssignIntent
                .Builder()
                .target(MainActivity.class)
                .falg(Intent.FLAG_ACTIVITY_NEW_TASK)
                .extra("name", "test");
        DefaultAssignIntent defaultAssignIntent1 = builder.build();
    }
}
