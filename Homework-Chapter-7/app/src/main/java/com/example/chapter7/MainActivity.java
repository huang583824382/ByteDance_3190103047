package com.example.chapter7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String TAG="chapter7";
    private String pictureUrl="https://sf3-hscdn-tos.pstatp.com/obj/inspirecloud-file/baas/tt41nq/6e7a0ba16cf3ac1d_1626744717540.png";
    private String pictureUrl_error="https://sf3-hscdn-tos.pstatp.com/obj/inspirecloud-file/baas/tt41nq/6e7a0ba16cf3ac1d_1626744717540.pn";
    private String imageurl;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View view = findViewById(R.id.main1);
        Button btn = findViewById(R.id.button);
        Button btn_error = findViewById(R.id.button_error);
        Button btnvideo = findViewById(R.id.buttonvideo);


        imageView= view.findViewById(R.id.image_test);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: YES");


            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imageurl=pictureUrl;
                    connect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imageurl=pictureUrl_error;
                    connect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void connect() throws MalformedURLException {

        RequestOptions options = new RequestOptions().placeholder(R.color.colorPrimary).bitmapTransform(new RoundedCorners(50)).error(R.mipmap.url_error);

        Glide.with(this).load(imageurl).transition(new DrawableTransitionOptions().crossFade()).apply(options).into(imageView);
    }

}
