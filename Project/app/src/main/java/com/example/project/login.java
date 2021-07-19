package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editTextuser=findViewById(R.id.editTextuser);
        final EditText editTextpass = findViewById(R.id.editTextpass);
        final Button buttonlogin=findViewById(R.id.buttonlogin);
        final Button buttonregister = findViewById(R.id.buttonsregister);
        // TODO 2 set other options
        final int[] flag = {0};
//        ObjectAnimator animator
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editTextuser.getText().toString();
                Log.d("----", "onClick: "+username);
            }
        });
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float screenWidth = getWindowManager().getDefaultDisplay().getWidth();
                float screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                float button_width=buttonregister.getWidth();
                Log.d("------", "onClick: "+screenWidth+" "+screenHeight);
                int[] position = new int[2];
                int[] positiontextuser = new int[2];
                editTextuser.getLocationInWindow(positiontextuser);
                Log.d("+++===", "onClick: "+positiontextuser[0]+" "+positiontextuser[1]);
                buttonregister.getLocationInWindow(position);
                Log.d("+++++", "onClick: "+position[0]+" "+position[1]);
                ValueAnimator animatorbutton = ValueAnimator.ofFloat(0f,1f);
                final float finalposbtn=0.5f*screenWidth-position[0]-button_width/2f;
                final float finalpostext=0.2f*screenHeight-positiontextuser[1];
                animatorbutton.setDuration(500);
                animatorbutton.setInterpolator(new AccelerateDecelerateInterpolator());

                if(flag[0]==0){
                    flag[0]=1;
                    buttonlogin.setVisibility(View.INVISIBLE);
                    animatorbutton.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float animatedValue = (float) animation.getAnimatedValue();
                            buttonregister.setTranslationX(animatedValue*finalposbtn);
                            editTextuser.setTranslationY(animatedValue*finalpostext);
                            editTextpass.setTranslationY(animatedValue*finalpostext);
                        }
                    });
                    animatorbutton.start();
                }
                else {
                    //TODO 1 login in
                }


            }
        });
    }
}
