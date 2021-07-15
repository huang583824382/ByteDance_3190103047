package com.bytedance.camp.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.bytedance.camp.chapter4.widget.Clock;
import com.bytedance.camp.chapter4.widget.MyviewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewgroup);
//
//        MyviewGroup myviewGroup= new  MyviewGroup(getApplicationContext());
    }
}
