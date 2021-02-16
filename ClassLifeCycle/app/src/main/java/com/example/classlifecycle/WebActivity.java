package com.example.classlifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView= findViewById(R.id.webViewId);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/");
    }

}