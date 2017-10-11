package com.example.bt_moudle1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by admin on 2017/9/20.
 */

public class SubmitResultActivity extends BaseActivity implements View.OnClickListener{

    private EditText edtShouSuoya;
    private EditText edtShuZhangya;
    private EditText edtMaibolv;
    private CheckBox chTingZheng;

    private Button btnCommit;
    private Button btnCancle;

    private TextView txtName;
    private TextView txtRoomNo;
    private TextView txtCurrentTime;
    private TextView txtKaoshiKemu;
    private TextView txtKaoshiShichang;
    Timer timer = new Timer();
    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (txtCurrentTime!=null){
                txtCurrentTime.setText("现在时间："+getNormalTime()+"（实时系统时间）");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_result);
        initView();
    }

    private void initView() {
        edtShouSuoya = (EditText) findViewById(R.id.edt_shousuoya);
        edtShuZhangya = (EditText) findViewById(R.id.edt_shuzhangya);
        edtMaibolv = (EditText) findViewById(R.id.edt_maibolv);
        chTingZheng = (CheckBox) findViewById(R.id.chb_tingzhengjianxi);

        btnCommit = (Button) findViewById(R.id.btn_start_exam);
        btnCancle = (Button) findViewById(R.id.btn_end_exam);
        btnCommit.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        txtName = (TextView) findViewById(R.id.txt_kaosheng_name);
        txtRoomNo = (TextView) findViewById(R.id.txt_kaosheng_fangjianhao);
        txtCurrentTime = (TextView) findViewById(R.id.txt_current_time);
        txtKaoshiKemu = (TextView) findViewById(R.id.txt_kaoshi_timu);
        txtKaoshiShichang = (TextView) findViewById(R.id.txt_kaoshi_shichnag);
        txtName.setText("考生姓名：张三");
        txtRoomNo.setText("考站房间号：301");
        txtCurrentTime.setText("现在时间："+getNormalTime()+"（实时系统时间）");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handle.sendMessage(new Message());
            }
        },0,1000);
        txtKaoshiKemu.setText("考试题目：血压考试A");
        txtKaoshiShichang.setText("考试时长：15分钟（点击开始考试，15分钟后系统将自动结束考试）");

    }


    @Override
    public void onClick(View view) {
        if (view == btnCancle){
            finish();
        }else if (view == btnCommit){
            String ShouSuoya = edtShouSuoya.getText().toString().trim();
            String ShuZhangya = edtShuZhangya.getText().toString().trim();
            String Maibolv = edtMaibolv.getText().toString().trim();
            boolean isCheck = chTingZheng.isChecked();

        }
    }

    private  String getNormalTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = format.format(new Date()) ;
        return time;
    }
}
