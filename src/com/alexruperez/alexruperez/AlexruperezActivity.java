package com.alexruperez.alexruperez;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;

import com.google.analytics.tracking.android.EasyTracker;
import com.flurry.android.FlurryAgent;

public class AlexruperezActivity extends Activity {
    /** Called when the activity is first created. */
    WebView mainWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainWebView = (WebView) findViewById(R.id.mainWebView);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new AlexruperezWebViewClient());
        mainWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mainWebView.loadUrl("http://alexruperez.com");
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
        FlurryAgent.onStartSession(this, "PBPF647394365NNTBTFG");
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
        FlurryAgent.onEndSession(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mainWebView != null && (keyCode == KeyEvent.KEYCODE_BACK) && mainWebView.canGoBack()) {
            mainWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class AlexruperezWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}