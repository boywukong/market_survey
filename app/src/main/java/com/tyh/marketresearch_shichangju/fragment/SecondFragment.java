package com.tyh.marketresearch_shichangju.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.activity.DetailPageActivity;
import com.tyh.marketresearch_shichangju.adapter.TopicListAdapter;

import java.util.Arrays;

/*
 * SecondFragment主要完成下面几件事：
 * 1.加载主页面上的Tab2中的菜单布局 list view
 * 2. 给list view传递参数
 * 3.加载 list view数据
 * 4.设置 list view点击事件
 *
 **/
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建Fragment界面
        View contentView=inflater.inflate(R.layout.topic_listview, container, false);

        /*获取医院栏目column下的标题topic组*/
        Resources res =getResources();
        final String[] topics=res.getStringArray(R.array.topic_array_2);

        //界面的初始化
        TopicListAdapter topicListAdapter=new TopicListAdapter(getContext(), Arrays.asList(topics));
        ListView listView= contentView.findViewById(R.id.topic_listview);
        listView.setAdapter(topicListAdapter);

        /*跳转activity组*/
        final String[] topiccodes=res.getStringArray(R.array.topiccode_array_2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                // Bundle传递数据
                                                Bundle bundle = new Bundle();
                                                bundle.putString("fragcode","02");
                                                bundle.putString("topic",topics[position]);
                                                bundle.putString("topiccode",topiccodes[position]);
                                                bundle.putString("position",Integer.toString(position));

                                                Intent intent = new Intent(getContext(), DetailPageActivity.class);

                                                // 向intent中传递bundle数据
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        }
        );

        return contentView;
    }
}