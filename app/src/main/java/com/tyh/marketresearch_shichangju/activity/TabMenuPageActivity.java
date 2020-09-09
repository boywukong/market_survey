package com.tyh.marketresearch_shichangju.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.annotation.TargetApi;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.util.ActivityUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabMenuPageActivity extends AppCompatActivity {

    private WebView mWebView;
    private List<String> titles =new ArrayList<>(Arrays.asList("医院","现场"));
    private List<Fragment> fragments;
    private int[] tabIcons = {
            R.mipmap.yiyuan_01,
            R.mipmap.xianchang_01
    };
    private int[] tabIconsPressed = {
            R.mipmap.yiyuan_02,
            R.mipmap.xianchang_02
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu_page);

        initHeader();
        initTabLayout();
        //Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        //建立目录
        Log.i("----MainActivity----", "checkAndCreateDir");
        checkAndCreateDir();

        Log.i("----MainActivity----", "copyTemplates");
        //拷模板文件
        copyTemplates(ActivityUtil.WENJUAN_01_PREFIX+".ftl");
        copyTemplates(ActivityUtil.WENJUAN_02_PREFIX+".ftl");
        copyTemplates(ActivityUtil.WENJUAN_03_PREFIX+".ftl");
        copyTemplates(ActivityUtil.WENJUAN_04_PREFIX+".ftl");
        copyTemplates(ActivityUtil.WENJUAN_05_PREFIX+".ftl");

        //Tab组件的点击功能设定
        final TabLayout m_tabLayout = this.findViewById(R.id.main_activity_tablayout);
        m_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                 @Override
                                                 public void onTabSelected(TabLayout.Tab tab) {

                                                     //Log.d("TabItem getPosition():" , " "+tab.getPosition());
                                                     int position = tab.getPosition();
                                                     //m_viewPager2.setCurrentItem(position, false);
                                                     setCurrentFragment(position);
                                                 }

                                                 //fragmen跳转
                                                 private void setCurrentFragment(int position) {
                                                     if (position == 0) {
                                                         Navigation.findNavController(TabMenuPageActivity.this, R.id.fragment).navigate(R.id.firstFragment);
                                                     } else {
                                                         Navigation.findNavController(TabMenuPageActivity.this, R.id.fragment).navigate(R.id.secondFragment);
                                                     }
                                                 }

                                                 @Override
                                                 public void onTabUnselected(TabLayout.Tab tab) {

                                                 }

                                                 @Override
                                                 public void onTabReselected(TabLayout.Tab tab) {

                                                 }
                                             }
        );
    }

    private void initHeader(){
        //html5页面
        WebView webview = (WebView) this.findViewById(R.id.headerview);
        Log.i("----MainActivity----","file:///android_asset/html/header.html");

        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setLoadWithOverviewMode(true);
        initView(webview);

        webview.loadUrl("file:///android_asset/html/header.html");
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    private void initView(WebView webView) {
        this.mWebView = webView;
        //可以执行javascript
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        //android添加javascript代码，让H5页面能够调用，第二个参数对应的是H5的
        this.mWebView.addJavascriptInterface(new TabMenuPageActivity.PayJavaScriptInterface(), "js");
    }

    private class PayJavaScriptInterface {
        @JavascriptInterface
        //跳转到首页
        public void jumpActivity(int param) {
            ActivityUtil.jumpActivity(TabMenuPageActivity.this,param);
        }
    }

    private void initTabLayout(){
        final TabLayout m_tabLayout = this.findViewById(R.id.main_activity_tablayout);
        m_tabLayout.getTabAt(0).setCustomView(getTabView(0));
        m_tabLayout.getTabAt(1).setCustomView(getTabView(1));
    }

    private void copyTemplates(String tempFileName) {
        //模板目录
        File tempFile = new File(getTemplateFilesDir());
        if(!tempFile.exists()){
            tempFile.mkdir();
        }

        Log.i("----copyTemplates:----",tempFileName);
        final String fileName = tempFileName;
        final File file = new File(getTemplateFilesDir(),fileName);
        if(file.exists()){//文件存在了就不需要拷贝了
            System.out.println("数据库文件已经存在,不需要再拷贝");
            return;
        }
        new Thread(){
            public void run() {
                System.out.println("进行数据库文件拷贝");
                try {
                    //获取资产目录管理器
                    AssetManager assetManager = getAssets();
                    InputStream is = assetManager.open("template/"+fileName);//输入流
                    FileOutputStream fos = new FileOutputStream(file);//输出流

                    Log.i("----AbsolutePath:----",file.getAbsolutePath());
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len=is.read(buffer))!=-1){
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    private String getTemplateFilesDir(){
        return Environment.getExternalStorageDirectory().toString()
                + File.separator
                + ActivityUtil.TEMP_DIR
                + File.separator;
    }

    private String getInquireFilesDir(){
        return Environment.getExternalStorageDirectory().toString()
                + File.separator
                + ActivityUtil.INQUIRE_DIR
                + File.separator;
    }

    private void checkAndCreateDir(){
        //模板目录
        File tempFile = new File(getTemplateFilesDir());
        if(!tempFile.exists()){
            boolean res = tempFile.mkdirs();
            Log.i("----TemplateDir:----",res+"");
        }

        //问卷目录
        File inquireFile = new File(getInquireFilesDir());
        Log.i("----InquireDir:----",inquireFile.getAbsolutePath());
        if(!inquireFile.exists()){
            boolean res = inquireFile.mkdirs();
            Log.i("----TemplateDir:----",res+"");
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        img_title.setPadding(-5, 0, -5, 0);

        img_title.setImageResource(tabIconsPressed[position]);
        //这里面有个bug,tab不会根据点选改变不同样式的图片
        //参见原文例子及demo:http://dditblog.com/itshare_616.html
        /*if (position == 0) {
            txt_title.setTextColor(Color.YELLOW);
            img_title.setImageResource(tabIconsPressed[position]);
            img_title.setPadding(-5,0,-5,0);
        } else {
            txt_title.setTextColor(Color.WHITE);
            img_title.setImageResource(tabIcons[position]);
        }*/
        return view;
    }
}