package com.bawei.day1220.view.activity;


import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.bawei.day1220.MyJavascriptInterface;
import com.bawei.day1220.R;
import com.bawei.day1220.base.BaseActivity;
import com.bawei.day1220.base.mvp.BasePresenter;

public class SecondActivity extends BaseActivity {

    private WebView webView;//声明WebView
    private Button butt;//声明button

    @Override
    protected BasePresenter initPresenter() {//不使用，不做处理
        return null;
    }

    @Override
    protected void initData() {

        webView.loadUrl("file:///android_asset/index.html");//加载本地网页
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                webView.loadUrl("file:///android_asset/index.html");//在程序内部做处理
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.wv);//找控件并初始化WebView
        butt = findViewById(R.id.butt);//找控件并初始化button
        webView.getSettings().setJavaScriptEnabled(true);//设置WebView支持JavaScript


        butt.setOnClickListener(new View.OnClickListener() {//button的点击事件
            @Override
            public void onClick(View view) {
                //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                webView.loadUrl("javascript:jsFunction2('我来自于adnroid的世界')");
            }
        });

        //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        MyJavascriptInterface myJavascriptInterface = new MyJavascriptInterface(this);
        //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        webView.addJavascriptInterface(myJavascriptInterface,"myJavascriptInterface");

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;//加载根布局
    }
}
