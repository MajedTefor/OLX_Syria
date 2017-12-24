package com.olx.smartlife_solutions.olx_syria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import static com.olx.smartlife_solutions.olx_syria.MainApp.checkInternet;
import static com.olx.smartlife_solutions.olx_syria.MainApp.fragmentTransaction;

public class Rules extends AppCompatActivity {
    WebView webView;
    public static final String rulesWebPage = "https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rules");
        webView = findViewById(R.id.showWebPage);
        webView.loadUrl(rulesWebPage);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (!checkInternet.isConnected()) {
            fragmentTransaction = getFragmentManager().beginTransaction();
      //      fragmentTransaction.replace(R.id.rulesView, noInternetFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return false;
    }
}
