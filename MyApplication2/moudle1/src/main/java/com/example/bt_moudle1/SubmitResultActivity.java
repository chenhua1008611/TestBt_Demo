package com.example.bt_moudle1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.scnu.zhou.widget.NumberKeyboard;

/**
 * Created by admin on 2017/9/20.
 */

public class SubmitResultActivity extends Activity {

    private EditText edtShouSuoya;
    private EditText edtShuZhangya;
    private EditText edtMaibolv;
    private CheckBox chTingZheng;
    private NumberKeyboard numberKeyboard;

    private Button btnCommit;
    private Button btnCancle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_result);
        initView();
    }

    private void initView() {
        numberKeyboard = (NumberKeyboard) findViewById(R.id.numberKeyboard);
        numberKeyboard.setOnInputListener(new NumberKeyboard.OnIuputListener() {
            @Override
            public void onInput(int num) {

                if (num != -1) {
                    // input number
                }
                else{
                    // backspace
                }
            }
        });
    }
}
