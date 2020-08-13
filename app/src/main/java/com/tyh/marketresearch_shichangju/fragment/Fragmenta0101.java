package com.tyh.marketresearch_shichangju.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.activity.DetailPageActivity;
import com.tyh.marketresearch_shichangju.adapter.TopicListAdapter;

import java.util.Arrays;

public class Fragmenta0101 extends Fragment {

    public Fragmenta0101() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建Fragment界面
        View contentView=inflater.inflate(R.layout.activity_detailpage_a0101, container, false);

        return contentView;
    }
}