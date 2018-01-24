package com.olx.smartlife_solutions.olx_syria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreviewAddActivity extends AppCompatActivity implements StaticStrings, API_URLs{

    String guid = "";

    ViewPager imgSlider;
    TextView dateTV,titleTV,usernameTV,priceTV,heartsTV,viewsTV,locationTV,desTV;

    RequestQueue requestQueue;


    //For Loading and failed view
    RelativeLayout loadingAndFailedParentRL, loadingRL;
    LinearLayout failedLL;
    Button failedRetryBtn;
    TextView statusCodeTV;

    int statusCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_add);
        guid = getIntent().getExtras().getString(PRE_GUID);
        init();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        PreviewImagesViewPagerAdapter imgsAdapter = new PreviewImagesViewPagerAdapter(getApplicationContext());
        imgSlider.setAdapter(imgsAdapter);
        downloadDataThenParse();

    }

    void downloadDataThenParse()
    {
        showLoadingView();
        StringRequest getMainAdsJson = new StringRequest(Request.Method.GET, ADS_URL + guid ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        parseJsonAndPutData(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        statusCode = volleyError.networkResponse.statusCode;
                        showFailedView();
                    }
                }
        );
        requestQueue.add(getMainAdsJson);
    }

    void init()
    {
        imgSlider = findViewById(R.id.itemsSliderVP);
        dateTV = findViewById(R.id.dateTV);
        titleTV = findViewById(R.id.titleTV);
        usernameTV = findViewById(R.id.userTV);
        priceTV = findViewById(R.id.priceTV);
        heartsTV = findViewById(R.id.heartsTV);
        viewsTV = findViewById(R.id.viewsTV);
        locationTV = findViewById(R.id.locationTV);
        desTV = findViewById(R.id.desTV);
        loadingAndFailedParentRL = findViewById(R.id.loadingAndFailedRL);
        loadingRL = findViewById(R.id.loadingViewRL);
        failedLL = findViewById(R.id.failedViewLL);
        failedRetryBtn = findViewById(R.id.failedRetryBtn);
        statusCodeTV = findViewById(R.id.statusCodeTV);
        failedRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadDataThenParse();
            }
        });
    }

    void parseJsonAndPutData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            titleTV.setText(jsonObject.getString(PRE_TITLE));
            dateTV.setText(jsonObject.getString(PRE_DATE));
            usernameTV.setText(jsonObject.getString(PRE_USER));
            priceTV.setText(jsonObject.getString(PRE_PRICE));
            heartsTV.setText(jsonObject.getString(PRE_HEARTS));
            viewsTV.setText(jsonObject.getString(PRE_VIEWS));
            String loc = (jsonObject.getString(PRE_COUNTRY).isEmpty() ? jsonObject.getString(PRE_COUNTRY)+", ":"")+
                    (jsonObject.getString(PRE_CITY).isEmpty() ? jsonObject.getString(PRE_CITY)+", ":"")+
                    (jsonObject.getString(PRE_AREA).isEmpty() ? jsonObject.getString(PRE_AREA):"");

            locationTV.setText(loc);
            desTV.setText(jsonObject.getString(PRE_DESCRIPTION));
            hideLoadingAndFailedView();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            showFailedView();
        }

    }


    void showFailedView()
    {
        statusCodeTV.setText("Status Code: " + statusCode);
        loadingAndFailedParentRL.setVisibility(View.VISIBLE);
        loadingRL.setVisibility(View.GONE);
        failedLL.setVisibility(View.VISIBLE);
    }

    void showLoadingView(){
        loadingAndFailedParentRL.setVisibility(View.VISIBLE);
        loadingRL.setVisibility(View.VISIBLE);
        failedLL.setVisibility(View.GONE);
    }

    void hideLoadingAndFailedView(){
        loadingAndFailedParentRL.setVisibility(View.GONE);
    }
}
