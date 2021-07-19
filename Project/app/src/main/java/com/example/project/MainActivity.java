package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
//        VideoView videoView = findViewById(R.id.videoView);
//        Log.d("ees", "onCreate: "+Environment.getExternalStorageDirectory() + "/Movies/VID_20210718_123053.mp4");
//        videoView.setVideoURI(Uri.parse("android.resource://" +getPackageName()+"/" + R.raw.test));
//        videoView.start();
    }
}
