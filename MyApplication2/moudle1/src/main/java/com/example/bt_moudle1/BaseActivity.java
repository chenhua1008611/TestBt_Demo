package com.example.bt_moudle1;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;


public abstract class BaseActivity extends FragmentActivity {

    protected Context mContext;
    private CustomLoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

//    showLoadingDialog(this, true, getResources().getString(R.string.text_loading_requesting));

    /**
     * 跳转界面并结束当前的Activity
     *
     * @param clazz
     */
    public void startActivityFinishSelf(Class<? extends Activity> clazz) {
        super.startActivity(new Intent(this, clazz));
        finish();
    }


    /**
     * 显示网络进度提示。登录专用。
     *
     * @param context 上下文环境
     */
    public void showLoadingDialog(Context context, boolean isCanBack, String msg) {
        try {
            if (loadingDialog == null) {
                loadingDialog = new CustomLoadingDialog(context, R.style.iphone_progress_dialog, msg);
                if (isCanBack) {
                    loadingDialog.setCancelable(true);//可取消

                } else {
                    loadingDialog.setCancelable(false);//不可取消

                }
                loadingDialog.setCanceledOnTouchOutside(false);//点击对话框外部不能取消
                loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onBackCancle();
                    }
                });
                if (context instanceof Activity
                        && !((Activity) context).isFinishing()) {
                    loadingDialog.show();
                }
            } else {
                if (context instanceof Activity
                        && !((Activity) context).isFinishing()) {
                    loadingDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackCancle() {
    }

    /**
     * 将进度提示框消失
     */
    public void dismissDialog() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
//                loadingDialog.cancel();
                loadingDialog.dismiss();
                loadingDialog = null;

            }
        } catch (Exception e) {

        }
    }


}