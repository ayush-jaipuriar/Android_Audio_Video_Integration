package com.example.audiovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    Button button;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        button = findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);
        mediaController = new MediaController(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        Uri videoUri = Uri.parse("android.resource://"+ getPackageName() + "/"
                        + R.raw.video);
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();


    }
}
