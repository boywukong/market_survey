package com.tyh.marketresearch_shichangju.adapter;

import android.content.ClipData;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
        ViewHolder viewHolder;
        //Log.i("----getView--position=",position+"");

        if(converView==null) {
            converView= LayoutInflater.from(context).inflate(R.layout.topic_listview_item, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) converView.findViewById(R.id.iv);
            viewHolder.textView = (TextView) converView.findViewById(R.id.topic_text);

            converView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) converView.getTag();
        }
        viewHolder.textView.addTextChangedListener(new MyTextWatcher(viewHolder));


        viewHolder.imageView.setTag(position);
        viewHolder.textView.setTag(position);

        String con= (String) getItem(position);
        TextView textView= converView.findViewById(R.id.topic_text);
        textView.setText(con);

        //Log.i("--viewHolder--textView=",viewHolder.textView.getTag()+"");

        return converView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    class MyTextWatcher implements TextWatcher {
        private ViewHolder viewHold;

        public MyTextWatcher(ViewHolder viewHold) {
            this.viewHold = viewHold;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //当前索引
            Object position = viewHold.textView.getTag();
        }
    }
}
