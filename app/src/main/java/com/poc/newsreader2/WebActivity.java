package com.poc.newsreader2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private static final String TAG = "WebActivity"; // Use `final` for constants

    // WebView declaration
    private WebView myWebView;

    // URL passed through the intent
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // Initialize WebView
        myWebView = findViewById(R.id.webview);

        // Get intent and retrieve the URL
//        Intent intent = getIntent();
        Intent intent = getSupportParentActivityIntent();
        if (intent != null && intent.hasExtra("url_key")) {
            url = intent.getStringExtra("url_key");
            if (url != null && !url.isEmpty()) {
                loadUrlInWebView(url);
            } else {
                Log.e(TAG, "URL is null or empty");
            }
        } else {
            Log.e(TAG, "Intent or URL not found");
        }
    }

    // Method to load the URL into WebView
    private void loadUrlInWebView(String url) {
        // Ensures links clicked in the WebView stay in the WebView
        myWebView.setWebViewClient(new WebViewClient());
        // Load the URL
        myWebView.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log resume state for debugging purposes
        Log.d(TAG, "WebActivity resumed");
    }
}
