package com.tyh.marketresearch_shichangju.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tyh.marketresearch_shichangju.R;

public class Fragmenta0103 extends Fragment {

    public Fragmenta0103() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建Fragment界面
        View contentView=inflater.inflate(R.layout.activity_detailpage_a0103, container, false);

        return contentView;
    }
}