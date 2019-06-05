package com.sfy.module.store.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lib_generic.base.SfyBaseActivity;
import com.sfy.module.store.R;

/**
 * 网页活动
 */
public class BaseWebActivity extends SfyBaseActivity {

    private WebView mTarget;

    @Override
    protected void initCommon() {
        super.initCommon();
        setContentView(R.layout.activity_web);

        mTarget = findViewById(R.id.target);
        String url = getIntent().getStringExtra("url");

        mTarget.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mTarget.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mTarget.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mTarget.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        mTarget.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mTarget.getSettings().setLoadWithOverviewMode(true);
        mTarget.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 return false;
            }

        });
        mTarget.loadUrl(url);
    }

}
