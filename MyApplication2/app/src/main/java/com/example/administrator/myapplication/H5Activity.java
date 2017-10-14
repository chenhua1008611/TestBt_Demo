package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.administrator.myapplication.utils.FileUtils;
import com.example.administrator.myapplication.utils.Json_U;

import java.io.File;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class H5Activity  extends Activity{

    public WebView mWebview;
    private ProgressBar progressbar_hori_webview;// webview 加载的进度
//    private String url = MyApplication.sApp.BASE_URL;
    private String url ;
    private String uploadUrl;
    private String uploadTuyaUrl;
    private Bean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String filepath = Environment.getExternalStorageDirectory()
                .toString() + "/osce.txt";
        String json = FileUtils.readFile(filepath);
        bean = Json_U.fromJson(json,Bean.class);
        url = bean.getBaseUrl();
        if (!url.endsWith("/")){
            url +="/";
        }
        uploadUrl = bean.getUploadUrl();
        uploadTuyaUrl = bean.getUploadUrl();
        if (!uploadUrl.endsWith("/")){
            uploadUrl +="/";
        }
        if (!uploadTuyaUrl.endsWith("/")){
            uploadTuyaUrl +="/";
        }
        uploadUrl +="Api/UploadFile";
        uploadTuyaUrl +="Api/UploadSign";
        initView();
    }

    private void initView() {
        progressbar_hori_webview = (ProgressBar) findViewById(R.id.progressbar_hori_webview);
        progressbar_hori_webview.setProgress(0);
        mWebview = (WebView) findViewById(R.id.web_content);
        WebSettings webseting = mWebview.getSettings();
        webseting.setDefaultTextEncodingName("utf-8");
        // 支持JavaScript
        webseting.setJavaScriptEnabled(true);
        webseting.setBuiltInZoomControls(true);
        webseting.setSupportZoom(true);
        // 支持保存数据
//        webseting.setSaveFormData(false);
        webseting.setSaveFormData(true);
        webseting.setDisplayZoomControls(false);//不显示webview缩放按钮
        webseting.setDomStorageEnabled(true);
//        String appCacheDir = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
//        webseting.setAppCachePath(appCacheDir);
        webseting.setLoadWithOverviewMode(true);
        webseting.setUseWideViewPort(true);
        webseting.setDatabaseEnabled(true);
        webseting.setAllowFileAccess(true);
//        webseting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webseting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webseting.setLoadsImagesAutomatically(true);
        mWebview.setWebChromeClient(new chooseProductWebChromeClient());
        mWebview.addJavascriptInterface(new MyObject(H5Activity.this), "myObj");
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebview.loadUrl(url);
    }

    /**
     * 加载进度显示
     */
    class chooseProductWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (100 == newProgress) {
                progressbar_hori_webview.setVisibility(View.GONE);
            } else {
                progressbar_hori_webview.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    // 覆盖onKeydown 添加处理WebView 界面内返回事件处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
//            mWebview.goBack();// 返回前一个页面
//            return true;
//        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == H5Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
//            mWebview.loadUrl("javascript:SaoESaoText('"+scanResult+"')");  //java调用js的函数
//            webView.loadUrl("javascript:showTime(\"" + id + "\",\"" + time + "\");");
            mWebview.loadUrl("javascript:showTime(\"" + scanResult + "\");");
        }
    }


    public class MyObject{
        private Context context;
        public MyObject(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void gotoBt(String ExamId,String stuId,String roomId,String empId,String questId){
            Intent intent = new Intent(H5Activity.this, MainActivity.class);
//            Intent intent = new Intent();
//            intent.setAction("com.example.bt_moudle1.BtMainActivity");
            intent.putExtra("ExamId",ExamId);
            intent.putExtra("stuId",stuId);
            intent.putExtra("roomId",roomId);
            intent.putExtra("empId",empId);
            intent.putExtra("questId",questId);
            startActivity(intent);
        }

        @JavascriptInterface
        public void gotoSaoyiSao(){
            Intent intent = new Intent(H5Activity.this, CaptureActivity.class);
            startActivityForResult(intent, 0);
        }

        @JavascriptInterface
        public void gotoTuya(String path,String json){
            Intent intent = new Intent(H5Activity.this, TuyaActivity.class);
            intent.putExtra("path",path);
            intent.putExtra("json",json);
            intent.putExtra("upload_url",uploadTuyaUrl);
            startActivity(intent);
        }

        @JavascriptInterface
        public void gotoContent(String path,String type, String json){
            if ("png".equals(type)||"jpg".equals(type)||"jpeg".equals(type)){
                File file = new File(path);
                if (file.exists()){
                    Intent intent = new Intent(H5Activity.this, PicActivity.class);
                    intent.putExtra("path",path);
                    intent.putExtra("json",json);
                    intent.putExtra("upload_url",uploadUrl);
                    startActivity(intent);
                }else{
                    ToastUtil.showToast(H5Activity.this,"文件不存在");
                }

            }else if ("pdf".equals(type)){
                File file = new File(path);
                if (file.exists()){
                    Intent intent = new Intent(H5Activity.this, PDFActivity.class);
                    intent.putExtra("path",path);
                    intent.putExtra("json",json);
                    intent.putExtra("upload_url",uploadUrl);
                    startActivity(intent);
                }else{
                    ToastUtil.showToast(H5Activity.this,"文件不存在");
                }

            }
        }
    }

}
