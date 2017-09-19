package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class TuyaActivity extends Activity implements View.OnClickListener{

    private TuyaView tuyaView = null;
    private ImageView btnClose;
    private ImageView btnUpload;
    private String basePath;
    private String baseJson;
    private String uploadUrl;
    private ProgressDialog dialog;
    private File file  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuya);
        basePath = getIntent().getStringExtra("path");
        baseJson = getIntent().getStringExtra("json");
        uploadUrl = getIntent().getStringExtra("upload_url");
        tuyaView = (TuyaView) findViewById(R.id.tuya);
        btnClose = (ImageView) findViewById(R.id.btn_close);
        btnUpload = (ImageView) findViewById(R.id.btn_upload_one);
        btnClose.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnClose) {
            finish();
        }else if (view == btnUpload){
//            tuyaView.saveFile();
            getTuYaFile(tuyaView);
            String fileUrl = Environment.getExternalStorageDirectory()
                    .toString() + "/test.png";
            file = new File(fileUrl);
            if (!file.exists()){
                ToastUtil.showToast(TuyaActivity.this,"文件不存在");
            }else{
                showProgress();
                OkHttpUtils.post()
                        .addFile("mFile", "test.png", file)
//                        .url(MyApplication.sApp.BASE_UPLOAD_URL)
                        .url(uploadUrl)
                        .addParams("extend", baseJson)
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e("eeee","失败"+e.toString());
                                closeProgress();
                                ToastUtil.showToast(TuyaActivity.this,"上传失败");
                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("eeee","成功"+response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.optString("success");
                                    if (result.equals("true")){
                                        closeProgress();
                                        ToastUtil.showToast(TuyaActivity.this,"上传成功");
                                    }else{
                                        closeProgress();
                                        ToastUtil.showToast(TuyaActivity.this,"上传失败");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }
    }

    private void getTuYaFile(View view){
        // 获取屏幕
        View dView = view;
//        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "test.png";

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
//            tuyaView.undo();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showProgress(){
        dialog = new ProgressDialog(TuyaActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setMessage("正在上传...");
        dialog.show();
    }

    private void closeProgress(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
