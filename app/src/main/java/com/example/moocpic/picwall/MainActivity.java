package com.example.moocpic.picwall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moocpic.PicTestActivity;
import com.example.moocpic.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }


    private void checkPermission() {
        int writePermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "测试手机版本为：android 6.0以上--->未申请--->申请读写权限");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            Log.v(TAG, "测试手机版本为：android 6.0以上--->已申请");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {//允许
                Log.v(TAG, "测试手机版本为：android 6.0以上--->未申请--->申请读写权限--->成功！");
            } else {//拒绝
                Log.v(TAG, "测试手机版本为：android 6.0以上--->未申请--->申请读写权限--->失败！");
                Toast.makeText(this, "请赋予读写权限，否则应用将无法使用！", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void toPicLoad(View view) {
        startActivity(new Intent(this, PicTestActivity.class));
    }
}
