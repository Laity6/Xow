package com.ol.xow.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ol.xow.BuildConfig;
import com.ol.xow.R;

import java.io.File;
import java.util.Date;

public class TakePhotoActivity extends AppCompatActivity {
    public static final int REQUEST_TAKEPHOTOS_CODE = 0003;
    private TextView tvPhotos;
    private ImageView imgView;
    private String picturePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        imgView = (ImageView) findViewById(R.id.img_view);

        tvPhotos = (TextView) findViewById(R.id.tv_photos);
        tvPhotos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String pictureName = DateUtils.format(new Date(), DateUtils.YEAR_MONTH_TIME_1) + ".jpg";
                String LOCAL_PATH = getFilesDir().getPath();
                String BASE_STORAGE_PATH = LOCAL_PATH + "/my";
                String CAPTURE_PICTURE_PATH = BASE_STORAGE_PATH + "/capture";
                picturePath = FileUtils.createFilePath(CAPTURE_PICTURE_PATH, pictureName);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                //根据路径实例化图片文件
//                File photoFile = new File(picturePath);
//                //设置拍照后图片保存到文件中
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                //启动拍照activity并获取返回数据
//                startActivityForResult(intent, REQUEST_TAKEPHOTOS_CODE);


                File cameraPhoto = new File(picturePath);
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoUri = FileProvider.getUriForFile(
                        TakePhotoActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        cameraPhoto);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePhotoIntent, REQUEST_TAKEPHOTOS_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKEPHOTOS_CODE:
                if (picturePath != null) {
                    File photoFile = new File(picturePath);
                    if (photoFile.exists()) {
                        ImageLoader.getInstance().displayImage("content://" + photoFile.getPath(), imgView);
                    } else {
//                        ActivityUtils.showToast(mContext, "图片文件不存在");
                    }
                }
                break;
        }
    }
}
