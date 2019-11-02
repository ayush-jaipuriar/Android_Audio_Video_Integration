package com.example.audiovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    Button button;
    MediaController mediaController;
    Button playMusic, pauseMusic;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        button = findViewById(R.id.playVideo);
        button.setOnClickListener(MainActivity.this);
        playMusic = findViewById(R.id.playMusic);
        pauseMusic = findViewById(R.id.pauseMusic);
        mediaController = new MediaController(MainActivity.this);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public void onClick(View buttonView) {


        switch (buttonView.getId()) {

            case R.id.playMusic : {
                mediaPlayer.start();
                Log.i("myTag","Play Music Button is entered");
                break; }



            case R.id.playVideo : {
                Uri videoUri = Uri.parse("android.resource://"+ getPackageName() + "/"
                        + R.raw.video);
                videoView.setVideoURI(videoUri);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
                videoView.start();
                Log.i("video","Video case entered");
                break;
            }




            case R.id.pauseMusic : {
                mediaPlayer.stop();
                break;
            }
        }


    }
}
