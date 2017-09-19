package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.administrator.myapplication.utils.FileUtils;
import com.example.administrator.myapplication.utils.Json_U;

import java.io.File;

/**
 * Created by Administrator on 2017/6/10.
 */
public class LoadingActivity extends Activity {

    private TextView mImgWelcome;
    private Animation alphaAnimation;
    private Bean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bean = new Bean();
        bean.setBaseUrl("http://192.168.1.226:8031/");
        bean.setUploadUrl("http://192.168.1.226:8031/");
        mImgWelcome = (TextView) findViewById(R.id.welcome_bg);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.welcome_alpha);
        alphaAnimation.setFillEnabled(true); //启动Fill保持
        alphaAnimation.setFillAfter(true);  //设置动画的最后一帧是保持在View上面
        mImgWelcome.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        String filepath = Environment.getExternalStorageDirectory()
                                .toString() + "/osce.txt";
                        File file = new File(filepath);
                        if (!file.exists()){
                            String json = Json_U.toJson(bean);
                            FileUtils.writeFile(filepath,json);
                        }
                        goActivity();

                    }

                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });  //为动画设置监听
    }

    // 跳转到主界面
    protected void goActivity() {
        Intent intent = new Intent();
        intent.setClass(LoadingActivity.this, H5Activity.class);
        startActivity(intent);
    }
}
