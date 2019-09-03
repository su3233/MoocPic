package com.example.moocpic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moocpic.picwall.MainActivity;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicTestActivity extends AppCompatActivity {
    private static final String TAG = "PicTestActivity";
    @BindView(R.id.iv_pic_1)
    ImageView ivPic1;
    @BindView(R.id.iv_pic_2)
    ImageView ivPic2;
    private Bitmap mCurrentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_test);
        ButterKnife.bind(this);

        load0riginalSize(ivPic1);
        testInBitmap(ivPic2);
//        testPic0ptimize(ivPic2);
    }


    /**
     * 直接加载图片
     *
     * @param ivPic1
     */
    private void load0riginalSize(ImageView ivPic1) {
//        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String filePath = sdcard + "/pic_1.jpg";
//        mCurrentBitmap = BitmapFactory.decodeFile(filePath);
        AssetManager assets = this.getResources().getAssets();
        try {
            InputStream in = assets.open("pic_1.jpg");
            mCurrentBitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivPic1.setImageBitmap(mCurrentBitmap);
    }

    /**
     * 压缩图片
     *
     * @param ivPic1
     */
    private void testPic0ptimize(ImageView ivPic1) {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdcard + "/pic_1.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int width = options.outWidth;
        options.inSampleSize = width / 200;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        ivPic1.setImageBitmap(bitmap);
    }

    /**
     * InBitmap
     */
    public void testInBitmap(ImageView iv) {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdcard + "/pic_2.png";//照一张图片放在sd卡根目录下
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inBitmap = mCurrentBitmap;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        iv.setImageBitmap(bitmap);
    }


}
