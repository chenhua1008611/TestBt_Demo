package com.example.bt_moudle1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chenhao.bluetoothlib.utils.ToastUtils;

/**
 * Created by admin on 2017/9/20.
 */

public class ParamSettingActivity extends BaseActivity implements View.OnClickListener{

    private TextView txtXueyaGao;
    private TextView txtXueyaDi;
    private TextView txtXueyaZhengchang;
    private EditText edtShousuoya;
    private TextView txtMoshiChuzhenfa;
    private TextView txtMoshiTingzhenfa;
    private TextView txtMoshiChuzhentingzhengfa;
    private EditText edtShuzhangya;
    private TextView textView;
    private EditText txtExamShichang;
    private EditText txtMaobolvValue;
    private EditText txtZuidaPengzhangShuiping;
    private CheckBox chbTingzhengJianxi;
    private TextView txtMaiboQiangduThree;
    private TextView txtMaiboQiangduTwo;
    private TextView txtMaiboQiangduOne;
    private TextView txtKeluofuteValueOne;
    private TextView txtKeluofuteValueTwo;
    private TextView txtKeluofuteValueThree;
    private TextView txtKeluofuteValueFour;
    private TextView txtKeluofuteValueFive;
    private Button btnExamSave;
    private Button btnEndExamCancle;

    private void assignViews() {
        txtXueyaGao = (TextView) findViewById(R.id.txt_xueya_gao);
        txtXueyaGao.setOnClickListener(this);
        txtXueyaDi = (TextView) findViewById(R.id.txt_xueya_di);
        txtXueyaDi.setOnClickListener(this);
        txtXueyaZhengchang = (TextView) findViewById(R.id.txt_xueya_zhengchang);
        edtShousuoya = (EditText) findViewById(R.id.edt_shousuoya);
        txtMoshiChuzhenfa = (TextView) findViewById(R.id.txt_moshi_chuzhenfa);
        txtMoshiTingzhenfa = (TextView) findViewById(R.id.txt_moshi_tingzhenfa);
        txtMoshiChuzhentingzhengfa = (TextView) findViewById(R.id.txt_moshi_chuzhentingzhengfa);
        edtShuzhangya = (EditText) findViewById(R.id.edt_shuzhangya);
        textView = (TextView) findViewById(R.id.textView);
        txtExamShichang = (EditText) findViewById(R.id.txt_exam_shichang);
        txtMaobolvValue = (EditText) findViewById(R.id.txt_maobolv_value);
        txtZuidaPengzhangShuiping = (EditText) findViewById(R.id.txt_zuida_pengzhang_shuiping);
        chbTingzhengJianxi = (CheckBox) findViewById(R.id.chb_tingzheng_jianxi);
        txtMaiboQiangduThree = (TextView) findViewById(R.id.txt_maibo_qiangdu_three);
        txtMaiboQiangduTwo = (TextView) findViewById(R.id.txt_maibo_qiangdu_two);
        txtMaiboQiangduOne = (TextView) findViewById(R.id.txt_maibo_qiangdu_one);
        txtKeluofuteValueOne = (TextView) findViewById(R.id.txt_keluofute_value_one);
        txtKeluofuteValueTwo = (TextView) findViewById(R.id.txt_keluofute_value_two);
        txtKeluofuteValueThree = (TextView) findViewById(R.id.txt_keluofute_value_three);
        txtKeluofuteValueFour = (TextView) findViewById(R.id.txt_keluofute_value_four);
        txtKeluofuteValueFive = (TextView) findViewById(R.id.txt_keluofute_value_five);
        btnExamSave = (Button) findViewById(R.id.btn_exam_save);
        btnEndExamCancle = (Button) findViewById(R.id.btn_end_exam_cancle);
        btnExamSave.setOnClickListener(this);
        btnEndExamCancle.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_setting);
        assignViews();

    }


    @Override
    public void onClick(View view) {
        if (view == btnExamSave){
            ToastUtils.showShort(this,"保存成功!");
        }else if (view == btnEndExamCancle){
            finish();
        }
    }
}
