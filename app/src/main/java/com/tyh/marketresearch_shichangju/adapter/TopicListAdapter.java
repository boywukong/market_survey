package com.tyh.marketresearch_shichangju.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tyh.marketresearch_shichangju.R;

import java.util.ArrayList;
import java.util.List;

public class TopicListAdapter  extends BaseAdapter {

    private Context context;
    private List<String> topiclist;

    public TopicListAdapter(Context context, List<String> topiclist) {
        this.context = context;
        this.topiclist = topiclist;
    }

    @Override
    public int getCount() {
        return topiclist.size();
    }

    @Override
    public Object getItem(int position) {
        return topiclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        if(converView==null) {
            converView= LayoutInflater.from(context).inflate(R.layout.topic_listview_item, null);
        }
        String con= (String) getItem(position);
        TextView textView= converView.findViewById(R.id.topic_text);
        textView.setText(con);

        return converView;
    }
}
