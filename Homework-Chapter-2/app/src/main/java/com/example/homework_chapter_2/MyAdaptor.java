package com.example.homework_chapter_2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TestData> mDataset;
    AdapterView.OnItemClickListener clickListener;
    public MyAdaptor(List<TestData> m){
        mDataset = m;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0) return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        else return new MyimageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
            }
        });
        if(holder instanceof MyHolder){
            MyHolder m1=(MyHolder)holder;
            m1.onBind(position, mDataset.get(position-1));
        }
        else{
            MyimageHolder m2=(MyimageHolder)holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mDataset.size()+1;
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
                tv1=itemView.findViewById(R.id.t1);
                tv2=itemView.findViewById(R.id.t2);
                tv3=itemView.findViewById(R.id.t3);
        }

        public void onBind(int position, TestData data){
                int index=position;
                tv1.setText(new StringBuilder().append(index).append("."));
                tv2.setText(data.title);
                tv3.setText(data.hot);
                if(index<=3){
                    tv1.setTextColor(Color.parseColor("#ffd700"));
                }else{
                    tv1.setTextColor(Color.parseColor("#000000"));
                }
        }
        public void setOnClickListener(View.OnClickListener listener){
            if(listener!=null){
                //
            }
        }
        public void setOnLongClickListener(View.OnLongClickListener listener){
            if(listener!=null){
                //
            }
        }

    }
    public class MyimageHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        public MyimageHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.imageView1);
            img1.setImageResource(R.drawable.hotsearch);
        }
    }


}
