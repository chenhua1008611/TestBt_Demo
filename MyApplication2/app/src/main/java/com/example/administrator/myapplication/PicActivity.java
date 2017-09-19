package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class PicActivity extends Activity implements View.OnClickListener {

    private PhotoView imgPicOne;
    private PhotoView imgPicTwo;

    private ImageView btnClose;
    private ImageView btnUploadone;
    private List<String> fileNames = new ArrayList<>();

    private String basePath;
    private String baseJson;
    private String uploadUrl;
    private File fileone;
    private File filetwo;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        basePath = getIntent().getStringExtra("path");
        baseJson = getIntent().getStringExtra("json");
        uploadUrl = getIntent().getStringExtra("upload_url");
        initView();

    }

    private void initView() {
        imgPicOne = (PhotoView) findViewById(R.id.img_show_pic_one);
        imgPicTwo = (PhotoView) findViewById(R.id.img_show_pic_two);
        btnClose = (ImageView) findViewById(R.id.btn_close);
        btnUploadone = (ImageView) findViewById(R.id.btn_upload_one);
        btnClose.setOnClickListener(this);
        btnUploadone.setOnClickListener(this);
        File filebases = new File(basePath);
        printFile(filebases);
        if (fileNames != null && fileNames.size() > 0) {
            if (fileNames.size() == 1) {
                fileone = new File(basePath + fileNames.get(0));
                Glide.with(PicActivity.this)
                        .load(fileone)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgPicOne);
                imgPicTwo.setVisibility(View.GONE);

            } else if (fileNames.size() == 2) {
                fileone = new File(basePath + fileNames.get(0));
                Glide.with(PicActivity.this)
                        .load(fileone)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgPicOne);

                filetwo = new File(basePath + fileNames.get(1));
                Glide.with(PicActivity.this)
                        .load(filetwo)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgPicTwo);
            }

        } else {
            ToastUtil.showToast(PicActivity.this, "文件不存在");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnClose) {
            finish();
        } else if (view == btnUploadone) {
            if (!fileone.exists()) {
                ToastUtil.showToast(PicActivity.this, "文件不存在");
            } else {
                upload(fileNames.get(0),fileone,1);
                upload(fileNames.get(1),filetwo,2);
            }
        }
    }

    private void upload(String filename, File file, final int flag) {
        if (flag == 2){
            showProgress();
        }
        OkHttpUtils.post()
                .addFile("mFile", filename, file)
//                .url(MyApplication.sApp.BASE_UPLOAD_URL)
                .url(uploadUrl)
                .addParams("extend", baseJson)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("eeee", "失败" + e.toString());
                        if (flag == 2){
                            closeProgress();
                            ToastUtil.showToast(PicActivity.this, "上传失败");
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("eeee", "成功" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.optString("success");
                            if (result.equals("true")){
                                if (flag == 2){
                                    closeProgress();
                                    ToastUtil.showToast(PicActivity.this, "上传成功");
                                }

                            }else{
                                if (flag == 2){
                                    closeProgress();
                                    ToastUtil.showToast(PicActivity.this, "上传失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

    private void printFile(File file) {
        if (file.isFile()) {
            Log.e("eeee", "000" + "您给定的是一个文件");
        } else {
            File[] fileLists = file.listFiles(); // 如果是目录，获取该目录下的内容集合

            for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
                if (fileLists[i].getName().endsWith(".png")||fileLists[i].getName().endsWith(".jpg")||fileLists[i].getName().endsWith(".jpeg")) {
                    Log.e("eeee", "111" + fileLists[i].getName());
                    fileNames.add(fileLists[i].getName());
                }
                Log.e("eeee", "111" + fileLists[i].getName());
                if (fileLists[i].isDirectory()) {    //判断元素是不是一个目录
                    printFile(fileLists[i]);    //如果是目录，继续调用本方法来输出其子目录
                }
            }
        }
    }

    private void showProgress() {
        dialog = new ProgressDialog(PicActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setMessage("正在上传...");
        dialog.show();
    }

    private void closeProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
