package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Activity_test3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Button btn1=findViewById(R.id.button7);
        RecyclerView mrcv=findViewById(R.id.recyclerview0);
        mrcv.setLayoutManager(new LinearLayoutManager(this));
        List<TestData> mDataList = new ArrayList<>();
        for(int i=1; i<=20; i++){
            mDataList.add(new TestData("hello" + i, "100w"));
        }

        mrcv.setAdapter(new MyAdaptor(mDataList));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_test3.this, MainActivity.class);
                startActivity(intent);
                Log.d("Activity_test3", "back to main ");
            }
        });



    }
}
