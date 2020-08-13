package com.tyh.marketresearch_shichangju.activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.adapter.QuickFragmentPageAdapter;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0101;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0102;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0103;

import java.util.ArrayList;
import java.util.List;

public class DetailPageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        ViewPager viewPager = findViewById(R.id.detail_view_pager);
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new Fragmenta0101());
        list.add(new Fragmenta0102());
        list.add(new Fragmenta0103());

        QuickFragmentPageAdapter adapter = new QuickFragmentPageAdapter(getSupportFragmentManager(), list, null);
        viewPager.setAdapter(adapter);
    }
}