package com.jola.onlineedu.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.BindView;

/**
 * Created by jola on 2018/10/15.
 */

public class LoadWebUrlActivity extends SimpleActivity {


    @BindView(R.id.wv_load_web_url)
    WebView wv_load_web_url;

    private boolean isInflatedCompleted = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_web_url;
    }

    @Override
    protected void initEventAndData() {
        wv_load_web_url.setVisibility(View.INVISIBLE);
        showLoadingDialog();

        final String url = getIntent().getStringExtra("url");

      wv_load_web_url.setWebViewClient(new WebViewClient(){
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
              view.loadUrl(url);
              return true;
          }
      });

      wv_load_web_url.setWebChromeClient(new WebChromeClient(){
          @Override
          public void onProgressChanged(WebView view, int newProgress) {
//              super.onProgressChanged(view, newProgress);
              if (newProgress > 70){
                  if (isInflatedCompleted){
                      return;
                  }
                  hideLoadingDialog();
                  wv_load_web_url.setVisibility(View.VISIBLE);
                  isInflatedCompleted = true;
              }
          }
      });

        wv_load_web_url.loadUrl(url);
    }
}
