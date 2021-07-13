package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn1=findViewById(R.id.btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test toast", "onClick: ");
                Toast toast=Toast.makeText(getApplicationContext(),"Basic Toast message", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
    protected void onStart(){
        super.onStart();
        Log.d("MYActivity", " onStart");
    }
    protected void onResume(){
        super.onResume();
        Log.d("MYActivity", " onResume");
    }
    protected void onPause(){
        super.onPause();
        Log.d("MYActivity", " onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.d("MYActivity", " onStop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MYActivity", " onDestroy");
    }

}
