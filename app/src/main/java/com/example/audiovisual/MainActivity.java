package com.example.audiovisual;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    Button button;
    MediaController mediaController;
    Button playMusic, pauseMusic;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    AudioManager audioManager;

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
        seekBar = findViewById(R.id.seekBar);
        //Since audioManager wants a systemService ,we pass it and typecast it to AudioManager
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        //Each user's device may have a different maximum volume, so we need to access it

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //Lets also get the current volume of the user's device

        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //It makes sense to set the maximum value of the volume seekbar as the maxVolume

        seekBar.setMax(maxVolume);

        //Also we have to set the current volume as the current progress of the seekbar

        seekBar.setProgress(currentVolume);


        // We have something called onSeekBar method which will deal with the changes in seekbar

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // This is an anonymous Inner Class that is implementing the OnSeekBarChangeListener method

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
