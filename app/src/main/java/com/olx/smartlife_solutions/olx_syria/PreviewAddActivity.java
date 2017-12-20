package com.olx.smartlife_solutions.olx_syria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreviewAddActivity extends AppCompatActivity implements StaticStrings{

    String json = "";

    ViewPager imgSlider;
    TextView dateTV,titleTV,usernameTV,priceTV,heartsTV,viewsTV,locationTV,desTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_add);

        init();

        PreviewImagesViewPagerAdapter imgsAdapter = new PreviewImagesViewPagerAdapter(getApplicationContext());
        imgSlider.setAdapter(imgsAdapter);

        parseJsonAndPutData();
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
    }

    void parseJsonAndPutData() {
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

            locationTV.setText(jsonObject.getString(loc));
            desTV.setText(jsonObject.getString(PRE_DESCRIPTION));
        } catch (JSONException e) {}

    }
}
