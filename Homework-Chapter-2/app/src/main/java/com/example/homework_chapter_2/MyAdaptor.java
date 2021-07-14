package com.example.homework_chapter_2;

import android.graphics.Color;
import android.util.Log;
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

    private int total;
    private List<TestData> mDataset;
    private IOnItemClickListener mItemClickListener;
    private IOnItemScrollListener mItemScrollListener;
    private int p;
    public MyAdaptor(List<TestData> m, int move){
        total=0;
        mDataset = m;
        p=move;
    }


    public interface IOnItemClickListener {

        void onItemCLick(int position, TestData data);

        void onItemLongCLick(int position, TestData data);
    }

    public interface IOnItemScrollListener {

        void onScroll(int position, TestData data);

    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemScrollListener(IOnItemScrollListener listener) {
        mItemScrollListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0) return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        else return new MyimageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof MyHolder){
            final MyHolder m1=(MyHolder)holder;
            m1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        m1.settv2color();
                        mItemClickListener.onItemCLick(position, mDataset.get(position-1));
                    }
                }
            });
            m1.onBind(position, mDataset.get(position-1));
        }
        else{
            final MyimageHolder m2=(MyimageHolder)holder;
            m2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                    }
                }
            });
            m2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    total+=dy;
                    Log.d("test scroll listener", "onScrolled: "+total);
                }
            });
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
        private View contentView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
                contentView=itemView;
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
        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {

                contentView.setOnClickListener(listener);
            }
        }
        public void settv2color(){
            tv2.setBackgroundColor(android.graphics.Color.RED);
        }

        public void setOnLongClickListener(View.OnLongClickListener listener){
            if(listener!=null){
                //
            }
        }

    }


    public class MyimageHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        private View contentView;
        public MyimageHolder(@NonNull View itemView) {
            super(itemView);
            contentView=itemView;
            img1=itemView.findViewById(R.id.imageView1);
            img1.setImageResource(R.drawable.hotsearch);
        }
        public void setimg(int total){
            float temp= (float) (1.0-total/300.0);
            if(temp<0) temp=0;
            img1.setAlpha(temp);
            Log.d("test", ""+img1.getAlpha());
        }
        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }
        public void addOnScrollListener(RecyclerView.OnScrollListener listener){
            if(listener!=null){

            }
        }
    }


}
