package com.example.admin.mycustomcontrol.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.admin.mycustomcontrol.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zq on 2017/3/2.
 * gradle
 *
 */

public class WedViewActivity extends BaseActivity {

    @BindView(R.id.wed_view)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedlayout);
        ButterKnife.bind(this);
        // 清除浏览器缓存
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);// 设定支持viewport
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);// 设定支持缩放
        settings.setBlockNetworkImage(false); // 设置图片渲染
        settings.setJavaScriptEnabled(true);
        //自适应屏幕:页面居中显示
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                // handler.cancel();// Android默认的处理方式environment
                handler.proceed();// 接受所有网站的证书
                // handleMessage(Message msg);// 进行其他处理
            }
        });
        mWebView.loadUrl("https://onlineuat.cupdata.com:50005/dbesbsit/app/pinsettingSignN.html");
//        mWebView.loadUrl("https://onlineuat.cupdata.com:50005/dbesbsit/app/bidApply.html?url_seri_no=170302100954911866");


    }
}



