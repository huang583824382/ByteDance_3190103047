package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.botton1);
        final TextView tv1 = findViewById(R.id.text1);
        final ImageView img1 = findViewById(R.id.imageView2);
        final ProgressBar pgb1= findViewById(R.id.programbar1);
        final SeekBar sb1=findViewById(R.id.seekbar1);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int len=100+progress;
                int wid=(int) (len * 3 / 4);
                img1.setLayoutParams(new LinearLayout.LayoutParams(len, wid));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setVisibility(View.VISIBLE);
                tv1.setText("What a wonderful day!");
                if(pgb1.getVisibility()==View.GONE){
                    pgb1.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.VISIBLE);
                    Log.d("VISIBLE", "hint-visable");
                }
                else{
                    pgb1.setVisibility(View.GONE);
                    img1.setVisibility(View.GONE);
                    Log.d("INVISIBLE", "hint-invisable");
                }
            }
        });
    }
}
