package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.manager.RobustPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MediaplayerDemoActivity extends AppCompatActivity {
    RobustPlayer robustPlayer;
    private String uri = "http://od.open.qingting.fm/m4a/5a436ca87cb89146f2089737_8455491_64.m4a?u=786&channelId=235929&programId=8295766";
    private TextView destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer_demo);
        robustPlayer = new RobustPlayer(this);
        findViewById(R.id.media_player_title);
        destination = findViewById(R.id.media_player_destination);


    }


    public void pause(View view) {
        robustPlayer.pause();
    }

    public void play(View view) {
        robustPlayer.play(Uri.parse(uri));
        destination.setText(""+robustPlayer.getDuration());
    }

    public void start(View view) {
        robustPlayer.start();
    }

    public void stop(View view) {
        robustPlayer.stop();
    }
}
