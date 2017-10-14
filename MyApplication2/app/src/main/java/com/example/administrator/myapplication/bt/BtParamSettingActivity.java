package com.example.administrator.myapplication.bt;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenhao.bluetoothlib.utils.ToastUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.Json_U;
import com.example.administrator.myapplication.utils.SPUtil;

/**
 * Created by admin on 2017/9/20.
 */

public class BtParamSettingActivity extends BtBaseActivity implements View.OnClickListener{

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
    private ImageView imgBack;
    private BtParamBean paramBean;

    private void assignViews() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        txtXueyaGao = (TextView) findViewById(R.id.txt_xueya_gao);
        txtXueyaGao.setOnClickListener(this);
        txtXueyaDi = (TextView) findViewById(R.id.txt_xueya_di);
        txtXueyaDi.setOnClickListener(this);
        txtXueyaZhengchang = (TextView) findViewById(R.id.txt_xueya_zhengchang);
        txtXueyaZhengchang.setOnClickListener(this);
        edtShousuoya = (EditText) findViewById(R.id.edt_shousuoya);
        txtMoshiChuzhenfa = (TextView) findViewById(R.id.txt_moshi_chuzhenfa);
        txtMoshiChuzhenfa.setOnClickListener(this);
        txtMoshiTingzhenfa = (TextView) findViewById(R.id.txt_moshi_tingzhenfa);
        txtMoshiTingzhenfa.setOnClickListener(this);
        txtMoshiChuzhentingzhengfa = (TextView) findViewById(R.id.txt_moshi_chuzhentingzhengfa);
        txtMoshiChuzhentingzhengfa.setOnClickListener(this);
        edtShuzhangya = (EditText) findViewById(R.id.edt_shuzhangya);
        textView = (TextView) findViewById(R.id.textView);
        txtExamShichang = (EditText) findViewById(R.id.txt_exam_shichang);
        txtMaobolvValue = (EditText) findViewById(R.id.txt_maobolv_value);
        txtZuidaPengzhangShuiping = (EditText) findViewById(R.id.txt_zuida_pengzhang_shuiping);
        chbTingzhengJianxi = (CheckBox) findViewById(R.id.chb_tingzheng_jianxi);
        txtMaiboQiangduThree = (TextView) findViewById(R.id.txt_maibo_qiangdu_three);
        txtMaiboQiangduThree.setOnClickListener(this);
        txtMaiboQiangduTwo = (TextView) findViewById(R.id.txt_maibo_qiangdu_two);
        txtMaiboQiangduTwo.setOnClickListener(this);
        txtMaiboQiangduOne = (TextView) findViewById(R.id.txt_maibo_qiangdu_one);
        txtMaiboQiangduOne.setOnClickListener(this);
        txtKeluofuteValueOne = (TextView) findViewById(R.id.txt_keluofute_value_one);
        txtKeluofuteValueOne.setOnClickListener(this);
        txtKeluofuteValueTwo = (TextView) findViewById(R.id.txt_keluofute_value_two);
        txtKeluofuteValueTwo.setOnClickListener(this);
        txtKeluofuteValueThree = (TextView) findViewById(R.id.txt_keluofute_value_three);
        txtKeluofuteValueThree.setOnClickListener(this);
        txtKeluofuteValueFour = (TextView) findViewById(R.id.txt_keluofute_value_four);
        txtKeluofuteValueFour.setOnClickListener(this);
        txtKeluofuteValueFive = (TextView) findViewById(R.id.txt_keluofute_value_five);
        txtKeluofuteValueFive.setOnClickListener(this);
        btnExamSave = (Button) findViewById(R.id.btn_exam_save);
        btnEndExamCancle = (Button) findViewById(R.id.btn_end_exam_cancle);
        btnExamSave.setOnClickListener(this);
        btnEndExamCancle.setOnClickListener(this);
        chbTingzhengJianxi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    paramBean.setTingZhengJianxi("1");
                }else{
                    paramBean.setTingZhengJianxi("0");
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_setting);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        paramBean = new BtParamBean();
        assignViews();
        changeView();

    }

    private void changeView() {
        String json = (String) SPUtil.get(this,"param_seting","");
        if (!TextUtils.isEmpty(json)){
            BtParamBean paramBean = Json_U.fromJson(json,BtParamBean.class);
            String xueYa = paramBean.getXueYa();
            String Moshi  = paramBean.getMoshi();
            String KaoshiShichang  = paramBean.getKaoshiShichang();
            String ZuidaPengzhang  = paramBean.getZuidaPengzhang();
            String MaiboQiangdu  = paramBean.getMaiboQiangdu();
            String TingZhengJianxi  = paramBean.getTingZhengJianxi();
            String KeLuoTe  = paramBean.getKeLuoTe();
            cleanMoshi();
            cleanKeluofuteValue();
            cleanMaiboQiangdu();
            cleanXueYa();
            if (xueYa.equals("1")){
                txtXueyaGao.setBackgroundResource(R.drawable.btn_case_pressed);
                edtShousuoya.setText("200");
                edtShuzhangya.setText("120");
                txtMaobolvValue.setText("105");
            }else if (xueYa.equals("2")){
                txtXueyaDi.setBackgroundResource(R.drawable.btn_case_pressed);
                edtShousuoya.setText("85");
                edtShuzhangya.setText("60");
                txtMaobolvValue.setText("80");
            }else if (xueYa.equals("3")){
                txtXueyaZhengchang.setBackgroundResource(R.drawable.btn_case_pressed);
                edtShousuoya.setText("120");
                edtShuzhangya.setText("85");
                txtMaobolvValue.setText("80");
            }

            if (Moshi.equals("1")){
                txtMoshiChuzhenfa.setBackgroundResource(R.drawable.shape_setting_param);
            }else if (Moshi.equals("2")){
                txtMoshiTingzhenfa.setBackgroundResource(R.drawable.shape_setting_param);
            }else if (Moshi.equals("3")){
                txtMoshiChuzhentingzhengfa.setBackgroundResource(R.drawable.shape_setting_param);
            }

            if (MaiboQiangdu.equals("1")){
                txtMaiboQiangduOne.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (MaiboQiangdu.equals("2")){
                txtMaiboQiangduTwo.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (MaiboQiangdu.equals("3")){
                txtMaiboQiangduThree.setBackgroundResource(R.drawable.btn_case_pressed);
            }

            if (KeLuoTe.equals("1")){
                txtKeluofuteValueOne.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (KeLuoTe.equals("2")){
                txtKeluofuteValueTwo.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (KeLuoTe.equals("3")){
                txtKeluofuteValueThree.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (KeLuoTe.equals("4")){
                txtKeluofuteValueFour.setBackgroundResource(R.drawable.btn_case_pressed);
            }else if (KeLuoTe.equals("5")){
                txtKeluofuteValueFive.setBackgroundResource(R.drawable.btn_case_pressed);
            }

            if (TingZhengJianxi.equals("1")){
                chbTingzhengJianxi.setChecked(true);
            }else{
                chbTingzhengJianxi.setChecked(false);
            }
            txtExamShichang.setText(KaoshiShichang);
            txtZuidaPengzhangShuiping.setText(ZuidaPengzhang);
        }

    }


    @Override
    public void onClick(View v) {
        if (v == btnExamSave){
            paramBean.setKaoshiShichang(txtExamShichang.getText().toString().trim());
            paramBean.setZuidaPengzhang(txtZuidaPengzhangShuiping.getText().toString().trim());
            String host_url = Json_U.toJson(paramBean);
            SPUtil.put(BtParamSettingActivity.this,"param_seting",host_url);
            ToastUtils.showShort(this,"保存成功!");
        }else if (v == btnEndExamCancle){
            paramBean.setKaoshiShichang("15");
            paramBean.setZuidaPengzhang("46");
            paramBean.setXueYa("3");
            paramBean.setMaiboQiangdu("3");
            paramBean.setKeLuoTe("3");
            paramBean.setMoshi("2");
            paramBean.setTingZhengJianxi("0");
            String host_url = Json_U.toJson(paramBean);
            SPUtil.put(BtParamSettingActivity.this,"param_seting",host_url);
            changeView();
        }else if (v == txtXueyaGao){
            cleanXueYa();
            txtXueyaGao.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setXueYa("1");
            edtShousuoya.setText("200");
            edtShuzhangya.setText("120");
            txtMaobolvValue.setText("105");
        }else if (v == txtXueyaDi){
            cleanXueYa();
            txtXueyaDi.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setXueYa("2");
            edtShousuoya.setText("85");
            edtShuzhangya.setText("60");
            txtMaobolvValue.setText("80");
        }else if (v == txtXueyaZhengchang){
            cleanXueYa();
            txtXueyaZhengchang.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setXueYa("3");
            edtShousuoya.setText("120");
            edtShuzhangya.setText("85");
            txtMaobolvValue.setText("80");
        }else if (v == imgBack){
            finish();
        }else if (v == txtMaiboQiangduOne){
            cleanMaiboQiangdu();
            txtMaiboQiangduOne.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setMaiboQiangdu("1");
        }else if (v == txtMaiboQiangduTwo){
            cleanMaiboQiangdu();
            txtMaiboQiangduTwo.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setMaiboQiangdu("2");
        }else if (v == txtMaiboQiangduThree){
            cleanMaiboQiangdu();
            txtMaiboQiangduThree.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setMaiboQiangdu("3");
        }else if (v == txtKeluofuteValueOne){
            cleanKeluofuteValue();
            txtKeluofuteValueOne.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setKeLuoTe("1");
        }else if (v == txtKeluofuteValueTwo){
            cleanKeluofuteValue();
            txtKeluofuteValueTwo.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setKeLuoTe("2");
        }else if (v == txtKeluofuteValueThree){
            cleanKeluofuteValue();
            txtKeluofuteValueThree.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setKeLuoTe("3");
        }else if (v == txtKeluofuteValueFour){
            cleanKeluofuteValue();
            txtKeluofuteValueFour.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setKeLuoTe("4");
        }else if (v == txtKeluofuteValueFive){
            cleanKeluofuteValue();
            txtKeluofuteValueFive.setBackgroundResource(R.drawable.btn_case_pressed);
            paramBean.setKeLuoTe("5");
        }else if (v == txtMoshiChuzhenfa){
            cleanMoshi();
            txtMoshiChuzhenfa.setBackgroundResource(R.drawable.shape_setting_param);
            paramBean.setMoshi("1");
        }else if (v == txtMoshiTingzhenfa){
            cleanMoshi();
            txtMoshiTingzhenfa.setBackgroundResource(R.drawable.shape_setting_param);
            paramBean.setMoshi("2");
        }else if (v == txtMoshiChuzhentingzhengfa){
            cleanMoshi();
            txtMoshiChuzhentingzhengfa.setBackgroundResource(R.drawable.shape_setting_param);
            paramBean.setMoshi("3");
        }
    }

    private void cleanMoshi(){
        txtMoshiChuzhenfa.setBackgroundResource(R.drawable.shape_setting_param_no_bg);
        txtMoshiTingzhenfa.setBackgroundResource(R.drawable.shape_setting_param_no_bg);
        txtMoshiChuzhentingzhengfa.setBackgroundResource(R.drawable.shape_setting_param_no_bg);
    }

    private void cleanKeluofuteValue(){
        txtKeluofuteValueOne.setBackgroundResource(R.drawable.btn_case_enable);
        txtKeluofuteValueTwo.setBackgroundResource(R.drawable.btn_case_enable);
        txtKeluofuteValueThree.setBackgroundResource(R.drawable.btn_case_enable);
        txtKeluofuteValueFour.setBackgroundResource(R.drawable.btn_case_enable);
        txtKeluofuteValueFive.setBackgroundResource(R.drawable.btn_case_enable);
    }

    private void cleanMaiboQiangdu(){
        txtMaiboQiangduOne.setBackgroundResource(R.drawable.btn_case_enable);
        txtMaiboQiangduTwo.setBackgroundResource(R.drawable.btn_case_enable);
        txtMaiboQiangduThree.setBackgroundResource(R.drawable.btn_case_enable);
    }

    private void cleanXueYa(){
        txtXueyaGao.setBackgroundResource(R.drawable.btn_case_enable);
        txtXueyaDi.setBackgroundResource(R.drawable.btn_case_enable);
        txtXueyaZhengchang.setBackgroundResource(R.drawable.btn_case_enable);
    }
}
