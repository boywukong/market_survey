package com.tyh.marketresearch_shichangju.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.adapter.QuickFragmentPageAdapter;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0101;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0102;
import com.tyh.marketresearch_shichangju.fragment.Fragmenta0103;
import com.tyh.marketresearch_shichangju.util.ActivityUtil;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * DetailPageActivity主要完成下面几件事：
 * 1.加载调查问卷详细页面布局 activity_detail_page
 * 2.接收上级菜单参数，匹配问卷内容页面Fragment0x0x
 *
 **/
public class DetailPageActivity extends AppCompatActivity {

    private static final String TAG = "DetailPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        String topic = bundle.getString("topic");
        String position = bundle.getString("position");
        String fragcode = bundle.getString("fragcode");

        ViewPager viewPager = findViewById(R.id.detail_view_pager);
        Fragment frg = new Fragmenta0101();
        Object ojb = null;

        List<Fragment> list = new ArrayList<Fragment>();

        Log.i("-DetailPageActy-pos=",(Integer.parseInt(position)+1)+"");


        try {
            frg = (Fragment)Class.forName("com.tyh.marketresearch_shichangju.fragment.Fragmenta"+fragcode+"0"+(Integer.parseInt(position)+1)).getConstructor(String.class).newInstance(topic);
        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        list.add(frg);

        QuickFragmentPageAdapter adapter = new QuickFragmentPageAdapter(getSupportFragmentManager(), list, null);viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //请求
            if (requestCode == ActivityUtil.LUYIN_REQUEST_CODE) {
                Log.e("--LUYIN_REQUEST_CODE--","-----------------LUYIN_REQUEST_CODE");
                //得到录音的音频文件及路径
                String dataFile = data.getDataString();
                Log.d(TAG, "dataFile: " + dataFile);
            }

            if(requestCode == ActivityUtil.PAIZHAO_REQUEST_CODE) {
                Log.e("--PAIZHAO_REQUEST_CODE--","-----------------PAIZHAO_REQUEST_CODE");

                Bitmap photo = null;
                //两种方式  获取拍好的图片
                if (data.getData() != null || data.getExtras() != null){  //防止没有返回结果
                    Uri uri = data.getData();
                    if (uri != null) {
                        try {
                            Log.e("--onActivityResult--uri--","-----------------"+uri);
                            Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        photo = BitmapFactory.decodeFile(uri.getPath());   //拿到图片
                        ActivityUtil.saveBitmap(ActivityUtil.getFilePath(), photo);
                    }
                    if (uri == null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            photo = (Bitmap) bundle.get("data");

                            Log.e("--(Bitmap) uri--","-----------------"+ActivityUtil.getFilePath());

                            ActivityUtil.saveBitmap(ActivityUtil.getFilePath(), photo);
                            Log.e("--(Bitmap) bundle--","-----------------"+photo.getByteCount());
                        } else {
                            Toast.makeText(getApplicationContext(), "找不到图片", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }


    }
}