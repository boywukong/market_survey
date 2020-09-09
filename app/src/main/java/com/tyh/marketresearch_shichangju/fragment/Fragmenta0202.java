package com.tyh.marketresearch_shichangju.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.independentsoft.xml.stream.XMLStreamException;
import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.util.ActivityUtil;
import com.tyh.marketresearch_shichangju.util.TemplateIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Fragmenta0202主要完成下面几件事：
 * 1.加载问卷4的布局和内容
 * 2.问卷4的页面内容：biao_05.html
 * 3.问卷4的按钮点击事件
 * 4.问卷4打印模板wenjuan_04_
 *
 **/
public class Fragmenta0202 extends Fragment {

    private String topic;
    private WebView mWebView;
    private String lastDocName;

    public Fragmenta0202() {
        // Required empty public constructor
        super();
    }

    public Fragmenta0202(String topic) {
        super();
        this.topic = topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLastDocName() {
        return lastDocName;
    }

    public void setLastDocName(String lastDocName) {
        this.lastDocName = lastDocName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup     container,
                             Bundle savedInstanceState) {
        //创建Fragment界面
        View contentView = inflater.inflate(R.layout.activity_detailpage_a0101, container, false);

        //html5页面
        WebView webview = (WebView) contentView.findViewById(R.id.webview);
        //webview.loadUrl("file:///android_asset/dcwj/dcwj_a0101.html");
        Log.i("----Fragmenta0202----","file:///android_asset/html/biao_05.html");

        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setLoadWithOverviewMode(true);
        initView(webview);

        webview.loadUrl("file:///android_asset/html/biao_05.html");
        return contentView;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    private void initView(WebView webView) {
        this.mWebView = webView;
        //可以执行javascript
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        //android添加javascript代码，让H5页面能够调用，第二个参数对应的是H5的
        this.mWebView.addJavascriptInterface(new PayJavaScriptInterface(), "js");
    }

    private class PayJavaScriptInterface {
        @JavascriptInterface
        //跳转到首页
        public void jumpActivity(int param) {
            ActivityUtil.jumpActivity(getActivity(),param);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @JavascriptInterface
        //保存调查数据
        public void inquireData(String gsonStr) throws IOException {

            Log.i("----inquireData----",gsonStr);
            /*Toast.makeText(getContext(), "inquireData : param = " + gsonStr, Toast.LENGTH_SHORT)
                    .show();
             */

            Gson gson = new Gson();
            Map paramMap = new HashMap();
            paramMap = gson.fromJson(gsonStr,Map.class);

            String templateName = "template/" + ActivityUtil.WENJUAN_04_PREFIX + ".docx";

            String targetUrl = Environment.getExternalStorageDirectory().toString()
                    + File.separator
                    + ActivityUtil.INQUIRE_DIR
                    + File.separator
                    + ActivityUtil.WENJUAN_04_PREFIX
                    + TemplateIO.getTemplateTime()
                    + ".docx";

            setLastDocName(targetUrl);
            //Log.i("----TemplateIO----", paramMap.toString());
            try {
                TemplateIO.saveAsTemplate(getContext(), templateName, paramMap, targetUrl);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }

            Toast.makeText(getContext(), "保存成功!", Toast.LENGTH_SHORT)
                    .show();

        }

        @JavascriptInterface
        public void toPrinter() {
            /*String targetUrl = Environment.getExternalStorageDirectory().toString()
                    + File.separator
                    + ActivityUtil.INQUIRE_DIR
                    + File.separator
                    + ActivityUtil.WENJUAN_04_PREFIX + ".docx";*/

            String targetUrl = getLastDocName();
            Log.i("----toPrinter----",targetUrl);
            Toast.makeText(getContext(), "toPrinter : uri = " + targetUrl, Toast.LENGTH_SHORT)
                    .show();

            /*Intent i = new Intent(Intent.ACTION_VIEW);
            i.setPackage("com.dynamixsoftware.printershare");
            i.setDataAndType(Uri.parse("content://"+targetUrl),"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            startActivity(i);*/

            Intent intent = new Intent();
            intent.setPackage("com.dynamixsoftware.printershare");
            intent.setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            intent.setData(Uri.parse("file://"+targetUrl));

            if(!ActivityUtil.hasAppsStartIntent(getContext(), intent)){
                Toast.makeText(getContext(), "没找到合适的打印应用软件，请您联系技术人员解决", Toast.LENGTH_SHORT)
                        .show();
            }

            startActivity(intent);
        }

        @JavascriptInterface
        public void toViewLuyin() {
            ActivityUtil.initViewLuyin(getActivity());
        }

        @JavascriptInterface
        public void toViewPaizhao() {
            ActivityUtil.initViewPaizhao(getActivity());
        }
    }
}