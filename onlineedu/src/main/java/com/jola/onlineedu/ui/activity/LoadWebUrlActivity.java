package com.jola.onlineedu.ui.activity;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_web_url;
    }

    @Override
    protected void initEventAndData() {
        final String url = getIntent().getStringExtra("url");

      wv_load_web_url.setWebViewClient(new WebViewClient(){
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
              view.loadUrl(url);
              return true;
          }
      });

        wv_load_web_url.loadUrl(url);
    }
}
