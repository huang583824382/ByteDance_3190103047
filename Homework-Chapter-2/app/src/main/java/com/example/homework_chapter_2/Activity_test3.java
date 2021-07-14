package com.example.homework_chapter_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity_test3 extends AppCompatActivity implements MyAdaptor.IOnItemClickListener,MyAdaptor.IOnItemScrollListener {
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


        final int[] total = {0};
        mrcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                total[0] = total[0] + dy;
                //Log.d("test scroll", "onScrolled: "+ total[0]);
            }
        });
        MyAdaptor myAdaptor=new MyAdaptor(mDataList, total[0]);
        myAdaptor.setOnItemClickListener(this);
        myAdaptor.setOnItemScrollListener(this);

        mrcv.setAdapter(myAdaptor);

//        mrcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//            }
//        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_test3.this, MainActivity.class);
                startActivity(intent);
                Log.d("Activity_test3", "back to main ");
            }
        });



    }
    public void onItemCLick(int position, TestData data) {
        Log.d("test click", "onItemCLick: get click "+position);

    }

    public void onItemLongCLick(int position, TestData data) {

    }

    @Override
    public void onScroll(int position, TestData data) {
        Log.d("111", "onScroll: ");
    }
}
