package com.example.chapter3.homework;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TestData> mDataset;
    private IOnItemClickListener mItemClickListener;
    public MyAdaptor(List<TestData> m){
        mDataset = m;
    }


    public interface IOnItemClickListener {

        void onItemCLick(int position, TestData data);
    }

    public void setmItemClickListener(IOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            final MyHolder m1=(MyHolder)holder;
            m1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemCLick(position, mDataset.get(position));
                    }
                }
            });
        Log.d("hello", "onBindViewHolder: "+position);
            m1.onBind(position, mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView tv1;
        TextView tv2;
        TextView tv3;

        private View contentView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
                contentView=itemView;
                tv1=itemView.findViewById(R.id.name);
                tv2=itemView.findViewById(R.id.message);
                tv3=itemView.findViewById(R.id.time);
                image1=itemView.findViewById(R.id.image1);
        }

        public void onBind(int position, TestData data){
                int index=position;
                tv1.setText(data.Name);
                tv2.setText(data.Message);
                tv3.setText(data.Time);
                image1.setImageResource(data.Image);
        }
        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }


    }





}
