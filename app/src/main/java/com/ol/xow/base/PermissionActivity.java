package com.ol.xow.base;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.ol.xow.base.permission.PermissionUtil;
import com.ol.xow.base.permission.ResultFunc;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class PermissionActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_STORAGE = 99;
    private PermissionUtil.PermissionRequestObject mRequestObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> list = new ArrayList<String>();

        list.add(Manifest.permission.CAMERA);
        list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        list.add(Manifest.permission.ACCESS_FINE_LOCATION);
        list.add(Manifest.permission.ACCESS_NETWORK_STATE);
        list.add(Manifest.permission.ACCESS_WIFI_STATE);
        list.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//        String[] permissions = new String[]{
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//        };
        String[] permissions = list.toArray(new String[list.size()]);
        // REQUEST_CODE_STORAGE is what ever int you want (should be distinct)
        mRequestObject = PermissionUtil.with(this)
                .request(permissions)
                .ask(REQUEST_CODE_STORAGE);
        mRequestObject.onResult(new ResultFunc() {
            @Override
            protected void call(int requestCode, String[] permissions, int[] grantResults) {
                Log.i("onResult>", "-----onResult\r\n" + JSON.toJSONString(permissions) + "\r\n" + JSON.toJSONString(grantResults));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestObject.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
