package com.tyh.marketresearch_shichangju.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.activity.MainActivity;
import com.tyh.marketresearch_shichangju.activity.TabMenuPageActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ActivityUtil {

    public final static int LUYIN_REQUEST_CODE = 111;
    public final static int PAIZHAO_REQUEST_CODE = 222;
    public final static String TEMP_DIR = "tempdata";
    public final static String INQUIRE_DIR = "inquiredata";
    public final static String WENJUAN_01_PREFIX = "wenjuan_01_";
    public final static String WENJUAN_02_PREFIX = "wenjuan_02_";
    public final static String WENJUAN_03_PREFIX = "wenjuan_03_";
    public final static String WENJUAN_04_PREFIX = "wenjuan_04_";
    public final static String WENJUAN_05_PREFIX = "wenjuan_05_";

    /**
     * 获取能启动intent的app信息
     *
     * @param context
     * @param intent
     * @return
     */
    public static List<ResolveInfo> getAppsForIntent(Context context,
                                                     Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        // 属性
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo info : resolveInfo) {
            Log.d("resolve info:" , info.activityInfo.packageName);
        }

        return resolveInfo;
    }

    /**
     * 是否有启动intent的app
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean hasAppsStartIntent(Context context, Intent intent) {
        List<ResolveInfo> appInfos = getAppsForIntent(context, intent);
        return appInfos != null && appInfos.size() > 0;
    }

    //调用系统录音应用
    public static void initViewLuyin(FragmentActivity activity) {
        //设置intent的属性为录音设置
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        activity.startActivityForResult(intent, LUYIN_REQUEST_CODE);
    }

    //调用系统拍照应用
    public static void initViewPaizhao(FragmentActivity activity) {
        //设置intent的属性为拍照设置
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, PAIZHAO_REQUEST_CODE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // 根据文件地址创建文件
        File file=new File(getFilePath());
        // 把文件地址转换成Uri格式
        Uri uri=Uri.fromFile(file);
        // 设置系统相机拍摄照片完成后图片文件的存放地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
    }

    public static String getFilePath() {
        String filePath = Environment.getExternalStorageDirectory().toString()
                + File.separator
                + ActivityUtil.INQUIRE_DIR
                + File.separator
                + TemplateIO.getTemplateTime()
                + ".BMP";
        return filePath;
    }

    //跳转到首页
    public static void jumpActivity(Activity activity, int param) {
        if (param == 1) {
            Intent it = new Intent();
            it.setClass(activity, MainActivity.class);
            activity.startActivity(it);

                /*Toast.makeText(getContext(), "跳转到Activity : param = " + param, Toast.LENGTH_SHORT)
                        .show();*/
        } else {
            Intent it = new Intent();
            it.setClass(activity, TabMenuPageActivity.class);
            activity.startActivity(it);

                /*Toast.makeText(getContext(), "跳转到Activity : param = " + param, Toast.LENGTH_SHORT)
                        .show();*/
        }
    }

    public static void saveBitmap(String bitmapUri, Bitmap mBitmap) {
        File f = new File(bitmapUri);
        try {
            f.createNewFile();
        } catch (IOException e) {
            Log.e("--saveBitmap--","在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
