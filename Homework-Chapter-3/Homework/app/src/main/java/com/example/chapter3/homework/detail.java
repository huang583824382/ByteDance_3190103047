package com.example.chapter3.homework;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        int img=intent.getIntExtra("image", 0);
        String n=intent.getStringExtra("name");
        Log.d("test intent", "onCreate: "+img+" "+n);
        TextView textView=findViewById(R.id.namedetail);
        ImageView image=findViewById(R.id.imagedetail);
        textView.setText(n);
        image.setImageResource(img);
    }
}
