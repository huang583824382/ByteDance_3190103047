package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity_test2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        Button btn4=findViewById(R.id.button4);
        Button btn0=findViewById(R.id.button0);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_test2.this, MainActivity.class);
                startActivity(intent);
                Log.d("Activity_test2", "confirm to main ");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_test2.this, MainActivity.class);
                startActivity(intent);
                Log.d("Activity_test2", "back to main ");
            }
        });

    }
}
