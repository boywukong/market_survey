package com.tyh.marketresearch_shichangju.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static Activity activity;
    /**
     * 获取context
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public static Context getContext() {
        if (activity == null) {
            return MyApplication.getInstance();
        }
        return activity;
    }
}