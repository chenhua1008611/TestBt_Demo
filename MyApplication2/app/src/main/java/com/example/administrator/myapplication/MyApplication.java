package com.example.administrator.myapplication;

import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class MyApplication extends MultiDexApplication {

    public String BASE_URL = "http://osceinvig.huilianonline.com/";
    public String BASE_UPLOAD_URL = "http://cdadmin.huilianonline.com/Api/UploadFile";
//    public String BASE_UPLOAD_URL = "http://cdadmin.huilianonline.com/Api/UploadSign";

    public static MyApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        BluetoothUtils.init(this);
    }
}
