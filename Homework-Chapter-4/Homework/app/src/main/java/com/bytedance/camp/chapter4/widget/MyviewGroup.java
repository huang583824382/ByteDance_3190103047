package com.bytedance.camp.chapter4.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytedance.camp.chapter4.R;

public class MyviewGroup extends ViewGroup {
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child0=getChildAt(0);
        View child1=getChildAt(1);

        int wid=getWidth();
        int len=getHeight();
        child0.layout(0, 0,wid,wid);
        child1.layout(450, wid+100, 1000,wid+300);
    }
    public MyviewGroup(Context context){
        this(context, null);
    }
    public MyviewGroup(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public  MyviewGroup(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        Clock clock = new Clock(getContext());
        TextView textView = new TextView(getContext());
        textView.setText("我的时钟");
        textView.setTextSize(20);
        textView.setTextColor(getResources().getColor(R.color.white));


        addView(clock);
        addView(textView);
    }
}
