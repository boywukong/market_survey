package com.tyh.marketresearch_shichangju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.tyh.marketresearch_shichangju.R;

import java.util.ArrayList;
import java.util.List;

public class MyViewPager2Adapter<view> extends RecyclerView.Adapter<MyViewPager2Adapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private ArrayList<View> viewLists;


    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};


    public MyViewPager2Adapter(Context context, List<String> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewPager2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewPager2Adapter.ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        holder.relativeLayout.setBackgroundResource(colorArray[position]);


    }

    @Override
    public int getItemCount() {
        return viewLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = itemView.findViewById(R.id.tvTitle);
            relativeLayout = itemView.findViewById(R.id.container);

        }
    }
}
