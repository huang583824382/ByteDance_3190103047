package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private String TAG = "test";
    private String video = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getApplicationContext().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_video);
        }
        else{
            setContentView(R.layout.fullscreen);
        }
        VideoView videoView=findViewById(R.id.videoView);
        videoView.setVideoPath(video);
        videoView.setMediaController(new MediaController(this));
        if(savedInstanceState!=null){
            pos=savedInstanceState.getInt("pos");
            Log.d(TAG, "onCreate: "+pos);
            videoView.seekTo(pos);
        }
        Dialog dialog= ProgressDialog.show(this, "正在加载…", "请稍后");

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(videoView.isPlaying()){
                    pos=videoView.getCurrentPosition();
                    Log.d(TAG, "run: "+pos);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                thread.start();
                dialog.dismiss();
            }
        });





    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("pos", pos);
        Log.d(TAG, "onSaveInstanceState: "+pos);
        super.onSaveInstanceState(outState);

    }


}