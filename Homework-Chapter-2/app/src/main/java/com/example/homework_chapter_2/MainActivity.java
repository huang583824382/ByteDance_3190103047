package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.button1);
        Button btn2=findViewById(R.id.button2);
        Button btn3=findViewById(R.id.button3);
        Button btn5=findViewById(R.id.button5);
        Button btn6=findViewById(R.id.button6);

        Log.d("MainActivity", "onCreate ");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_test.class);
                startActivity(intent);
                Log.d("jump", "to myActivity ");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                Log.d("jump", "to BaiDu ");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:15822222222"));
                startActivity(intent);
                Log.d("jump", "to my telephone ");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_test2.class);
                startActivity(intent);
                Log.d("MainActivity", "to mission 1 ");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_test3.class);
                startActivity(intent);
                Log.d("MainActivity", "to mission 2 ");
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity", " onStart");
    }
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", " onResume");
    }
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", " onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity", " onStop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity", " onDestroy");
    }
}
