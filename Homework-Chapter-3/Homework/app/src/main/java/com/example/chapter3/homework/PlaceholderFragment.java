package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment implements MyAdaptor.IOnItemClickListener {
    LottieAnimationView lottie;
    TextView tv1;
    RecyclerView rv1;
    //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view=inflater.inflate(R.layout.fragment_placeholder, container, false);
        lottie=view.findViewById(R.id.animation_view);
        tv1=view.findViewById(R.id.textview1);
        rv1=view.findViewById(R.id.recyclerview0);
        rv1.setLayoutManager(new LinearLayoutManager(rv1.getContext()));

        int path[]={R.drawable.head, R.drawable.head1, R.drawable.head2};
        String name[]={"hzw", "jx", "wzg"};
        List<TestData> mDataList = new ArrayList<>();
        for(int i=1; i<=20; i++){
            mDataList.add(new TestData(path[i%3], name[i%3]+i, "你好呀！","20:"+(60-i)));
        }
        MyAdaptor myAdaptor=new MyAdaptor(mDataList);

        myAdaptor.setmItemClickListener(this);
        rv1.setAdapter(myAdaptor);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
                valueAnimator.setDuration(1000);
                valueAnimator.setRepeatCount(0);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        lottie.setAlpha((float)valueAnimator.getAnimatedValue());
                        rv1.setAlpha(1f-(float)valueAnimator.getAnimatedValue());
                    }
                });
                valueAnimator.start();
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
            }
        }, 5000);
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        Intent intent = new Intent(getActivity(), detail.class);
        intent.putExtra("image", data.Image);
        intent.putExtra("name", data.Name);
        startActivity(intent);
    }
}
