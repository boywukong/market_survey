package com.tyh.marketresearch_shichangju.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.tyh.marketresearch_shichangju.R;
import com.tyh.marketresearch_shichangju.util.MResource;

import java.io.File;

public class ContentActivity extends AppCompatActivity {

    private ImageView btn_luyin;
    private ImageView btn_paizhao;
    private static final String TAG = "ContentActivity";
    private TextView tvRecorder;
    private final int REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yiyuan_1st_content);

        requestDir();

        initViewLuyin();
        initViewPaizhao();
    }

    //发起文件权限请求
    private void requestDir() {
        ActivityCompat.requestPermissions(ContentActivity.this, new String[]{android
                .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    //调用系统录音应用
    private void initViewLuyin() {
        btn_luyin = findViewById(R.id.imageView_luyin);
        btn_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置intent的属性为录音设置
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    static final Uri printFileUri = Uri.parse("file:///data/360/screenshot/screen_shot_to_share.jpg");
    //调用系统拍照应用
    private void initViewPaizhao() {
        btn_luyin = findViewById(R.id.imageView_paizhao);
        btn_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置intent的属性为拍照设置
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, REQUEST_CODE);

                /*Intent intent = new Intent();
                ComponentName comp = new ComponentName("com.dynamixsoftware.printershare","com.dynamixsoftware.printershare.ActivityPrintPictures");
                intent.setComponent(comp);
                intent.setAction("android.intent.action.VIEW");
                intent.setData(printFileUri);
                startActivity(intent);*/

                File filesDir = getFilesDir();
                String absolutePath = filesDir.getAbsolutePath();

                String outerPath = Environment.getExternalStoragePublicDirectory("").getAbsolutePath();

                Log.i("Tag","first activity--------onStop()");
                System.out.println("first activity--------onStop()");

                File addFile = new File(outerPath + "/test");
                addFile.mkdir();

                Log.i("AddFile",addFile.getAbsolutePath());
                System.out.println("--------AddFile"+addFile.getAbsolutePath());

                File file = new File("/storage/emulated/0/test");
                //storage/emulated/0/shichangju/screen_shot_to_share.jpg
                boolean res = addFile.exists();

                //AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
                //data/user/0/com.tyh.marketresearch_shichangbu/files
                //getExternalStoragePublicDirectory = /storage/emulated/0

                Toast.makeText(ContentActivity.this, res + "|||" + absolutePath + "|||" + outerPath,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //请求
            if (requestCode == REQUEST_CODE) {
                //得到录音的音频文件及路径
                String dataFile = data.getDataString();
                Log.d(TAG, "dataFile: " + dataFile);
            }
        }
    }
}