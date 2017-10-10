package com.example.bt_moudle1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by admin on 2017/10/10.
 */

public class CustomLoadingDialog extends ProgressDialog {

    private String msg;
    private TextView tvTip;

    public CustomLoadingDialog(Context context, int theme, String msg) {
        super(context,theme);
        this.msg = msg;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tvTip = (TextView) findViewById(R.id.tv_tip);
        tvTip.setText(msg);
    }

}
