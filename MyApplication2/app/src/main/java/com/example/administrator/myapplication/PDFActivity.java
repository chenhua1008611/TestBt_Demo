package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class PDFActivity extends Activity implements OnPageChangeListener ,View.OnClickListener{

    private int pageNumber = 1;
    private PDFView pdfview;
    private ImageView btnClose;
    private ImageView btnUpload;
    private String fileName;

    private String basePath;
    private String baseJson;
    private String uploadUrl;
    private File file  ;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        basePath = getIntent().getStringExtra("path");
        baseJson = getIntent().getStringExtra("json");
        uploadUrl = getIntent().getStringExtra("upload_url");
        pdfview = (PDFView) findViewById(R.id.pdfview);
        btnClose = (ImageView) findViewById(R.id.btn_close);
        btnUpload = (ImageView) findViewById(R.id.btn_upload);
        btnClose.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        File filebases = new File(basePath);
        printFile(filebases);
        file = new File(basePath+fileName);
        if (TextUtils.isEmpty(fileName)){
            ToastUtil.showToast(PDFActivity.this,"文件不存在");
        }else{
            if (file.exists()) {
                readPdf(file);
            }else{
                ToastUtil.showToast(PDFActivity.this,"文件不存在");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnClose){
            finish();
        }else if (view == btnUpload){
            if (!file.exists()){
                ToastUtil.showToast(PDFActivity.this,"文件不存在");
            }else{
                showProgress();
                OkHttpUtils.post()
                        .addFile("mFile", fileName, file)
//                        .url(MyApplication.sApp.BASE_UPLOAD_URL)
                        .url(uploadUrl)
                        .addParams("extend", baseJson)
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e("eeee","失败"+e.toString());
                                closeProgress();
                                ToastUtil.showToast(PDFActivity.this,"上传失败");
                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("eeee","成功"+response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.optString("success");
                                    if (result.equals("true")){
                                        closeProgress();
                                        ToastUtil.showToast(PDFActivity.this,"上传成功");
                                    }else{
                                        closeProgress();
                                        ToastUtil.showToast(PDFActivity.this,"上传失败");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }

        }
    }

    private void readPdf(File file) {
        pdfview.fromFile(file)
                .defaultPage(pageNumber)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
//        text.setText(page + "/" + pageCount);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void printFile(File file) {
        if (file.isFile()) {
            Log.e("eeee","000"+"您给定的是一个文件");
        } else {
            File[] fileLists = file.listFiles(); // 如果是目录，获取该目录下的内容集合

            for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
                if (fileLists[i].getName().endsWith(".pdf")){
                    Log.e("eeee","111"+fileLists[i].getName());
                    fileName = fileLists[i].getName();
                }
                Log.e("eeee","111"+fileLists[i].getName());
                if (fileLists[i].isDirectory()) {    //判断元素是不是一个目录
                    printFile(fileLists[i]);    //如果是目录，继续调用本方法来输出其子目录
                }
            }
        }
    }

    private void showProgress(){
        dialog = new ProgressDialog(PDFActivity.this);
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
