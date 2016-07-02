package com.example.tripathee.chatnrna;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Tripathee on 6/18/2016.
 */
public class NewsletterActivity extends Activity {

    private WebView webview;
    private View mContentView;
    String Url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //mContentView = inflater.inflate(R.layout.activity_news, container, false);

        //setHeader(getString(R.string.EclairActivityTitle), true, true);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);*/
        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            Url =extras.getString("pdf_name");
        }

        webview = (WebView) findViewById(R.id.webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl("http://nrna.org.np/images/newsletter/"+Url );
        //webview.setWebViewClient(new MyBrowser());


        webview.setDownloadListener(new DownloadListener() {

            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir("storage/sdcard1/download", Url);
                File extStore = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                System.out.println("CHECK" +request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Url));
                DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //This is important!
                intent.addCategory(Intent.CATEGORY_OPENABLE); //CATEGORY.OPENABLE
                intent.setType(".pdf");//any application,any extension
                Toast.makeText(NewsletterActivity.this, "Downloading File.. Wait for a second", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });
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
    }

   /* public String setURl(String Url){
        return this.Url = Url;

    }*/


}

class MyBrowserNews extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('full_wrap nav_menu_wrap')[0].style.display='none'; })()");
        view.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('full_wrap header_top')[0].style.display='none'; })()");
               // view.loadUrl("javascript:document.getElementByClassName(\"full_wrap nav_menu_wrap\").setAttribute(\"style\",\"display:none;\");");
    }
}
