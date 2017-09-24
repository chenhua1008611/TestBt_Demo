package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myapplication.utils.SPUtil;


public class MainActivity extends Activity implements View.OnClickListener{

    private EditText edtHost;
    private ImageView btnload;

    private EditText edtUpload;
    private ImageView btnsave;

    private String host_url;
    private String host_url_upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        initView();

    }

    private void initView() {
        edtHost = (EditText) findViewById(R.id.edt_name_host);
        btnload = (ImageView) findViewById(R.id.img_load_launch);
        btnload.setOnClickListener(this);

        edtUpload = (EditText) findViewById(R.id.edt_name_upload);
        btnsave = (ImageView) findViewById(R.id.img_load_save);
        btnsave.setOnClickListener(this);
        host_url = (String) SPUtil.get(MainActivity.this,"host_url","");
        if (!TextUtils.isEmpty(host_url)){
            edtHost.setText(host_url);
        }
        host_url_upload = (String) SPUtil.get(MainActivity.this,"host_url_upload","");
        if (!TextUtils.isEmpty(host_url_upload)){
            edtUpload.setText(host_url_upload);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnload){
            host_url = edtHost.getText().toString().trim();
            if (!host_url.endsWith("/")){
                host_url +="/";
            }
            SPUtil.put(MainActivity.this,"host_url",host_url);
            MyApplication.sApp.BASE_URL = host_url;
            Intent intent = new Intent(MainActivity.this, H5Activity.class);
            startActivity(intent);
        }else if (view == btnsave){
            host_url_upload = edtUpload.getText().toString().trim();
            if (!host_url_upload.endsWith("/")){
                host_url_upload +="/";
            }
            MyApplication.sApp.BASE_UPLOAD_URL = host_url_upload +"Api/UploadFile";
            SPUtil.put(MainActivity.this,"host_url",host_url);
            SPUtil.put(MainActivity.this,"host_url_upload",host_url_upload);
            ToastUtil.showToast(MainActivity.this,"保存成功");
        }

    }

}
