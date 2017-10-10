package com.example.bt_moudle1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chenhao.bluetoothlib.utils.ToastUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by admin on 2017/9/20.
 */

public class ParamSettingActivity extends Activity implements View.OnClickListener{

    private Button btnSave;
    private Button btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_setting);
        initView();

        OkHttpUtils
                .post()
                .url(Constant.GET_EXAM_STUDENT)
                .addParams("ExamId", "49")
                .addParams("stuId", "191")
                .addParams("roomId", "14")
                .addParams("empId", "54")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("tag===", "onError");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("tag===", "onResponse");
                        Log.e("tag===", response);

                    }
                });

    }

    private void initView() {
        btnSave = (Button) findViewById(R.id.btn_exam_save);
        btnSave.setOnClickListener(this);
        btnCancle = (Button) findViewById(R.id.btn_end_exam_cancle);
        btnCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSave){
            ToastUtils.showShort(this,"保存成功!");
        }else if (view == btnCancle){
            finish();
        }
    }
}
