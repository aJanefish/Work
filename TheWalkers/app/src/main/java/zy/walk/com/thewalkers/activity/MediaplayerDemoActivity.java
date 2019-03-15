package zy.walk.com.thewalkers.activity;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.manager.RobustPlayer;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


@ViewLayout(R.layout.activity_mediaplayer_demo)
public class MediaplayerDemoActivity extends BaseActivity {
    private static final String TAG = "MediaPlayerDemo";
    RobustPlayer robustPlayer;
    private String uri = "http://od.open.qingting.fm/m4a/5a436ca87cb89146f2089737_8455491_64.m4a?u=786&channelId=235929&programId=8295766";
    @ViewField(getId = R.id.media_player_destination)
    private TextView destination;
    @ViewField(getId = R.id.media_player_start)
    private Button start;
    @ViewField(getId = R.id.media_player_play)
    private Button play;
    @ViewField(getId = R.id.media_player_pause)
    private Button pause;
    @ViewField(getId = R.id.media_player_stop)
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.register(this);
    }

    @Override
    public void initView() {
        robustPlayer = new RobustPlayer(this);
    }

    @Override
    public void initDate() {
        Log.d(TAG, "start:" + start);
        Log.d(TAG, "stop:" + stop);
        Log.d(TAG, "play:" + play);
        Log.d(TAG, "pause:" + pause);
        Log.d(TAG, "destination:" + destination);
    }

    @Override
    protected String getLog() {
        return TAG;
    }

    private void show(String log){
        Log.d(TAG, "" + log);

    }


//    public void pause(View view) {
//        robustPlayer.pause();
//    }
//
//    public void play(View view) {
//        robustPlayer.play(Uri.parse(uri));
//        destination.setText("" + robustPlayer.getDuration());
//    }
//
//    public void start(View view) {
//        robustPlayer.start();
//    }
//
//    public void stop(View view) {
//        robustPlayer.stop();
//    }


    @ViewMethod(getId = R.id.media_player_pause)
    public void pause(View v) {
        robustPlayer.pause();
        show("pause");
    }

    @ViewMethod(getId = R.id.media_player_play)
    public void play(View v) {
        robustPlayer.play(Uri.parse(uri));
        show("play");

    }

    @ViewMethod(getId = R.id.media_player_stop)
    public void stop(View v) {
        robustPlayer.stop();
        show("stop");

    }

    @ViewMethod(getId = R.id.media_player_start)
    public void start(View v) {
        robustPlayer.start();
        show("start");
    }
}
