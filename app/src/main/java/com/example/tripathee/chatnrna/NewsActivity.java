package com.example.tripathee.chatnrna;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Tripathee on 6/8/2016.
 */
public class NewsActivity extends Fragment {

    private WebView webview;
    private View mContentView;
    ProgressBar pbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.activity_news, container, false);
        //setHeader(getString(R.string.EclairActivityTitle), true, true);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);*/
        pbar = (ProgressBar)  mContentView.findViewById(R.id.progressBar1);
        //progress.setVisibility(View.VISIBLE);
        webview = (WebView) mContentView.findViewById(R.id.webview);
        Toolbar toolbar = (Toolbar) mContentView.findViewById(R.id.toolbar);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl("http://nrna.org.np/news.php");
        //NewsActivity.this.progress.setProgress(0);
        webview.setWebViewClient(new MyBrowser());
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        return mContentView;
    }

    class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pbar.setVisibility(View.GONE);
            view.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('full_wrap nav_menu_wrap')[0].style.display='none'; })()");
            view.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('full_wrap header_top')[0].style.display='none'; })()");
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }
    }

   /* public void setValue(int progress) {
        this.progress.setProgress(progress);
    }*/
}