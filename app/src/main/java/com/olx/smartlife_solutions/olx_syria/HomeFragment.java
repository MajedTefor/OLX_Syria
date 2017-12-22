package com.olx.smartlife_solutions.olx_syria;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Majed-PC on 12/8/2017.
 */

public class HomeFragment extends Fragment implements StaticStrings{

    RecyclerView categoryRV;
    StaggeredGridView grid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        /// Category Slider
        categoryRV = view.findViewById(R.id.categoryRV);
        categoryRV.setHasFixedSize(true);


        LinearLayoutManager categoryRVLayout = new LinearLayoutManager(getContext());
        categoryRVLayout.setOrientation(LinearLayoutManager.HORIZONTAL);


        categoryRV.setLayoutManager(categoryRVLayout);

        RecyclerView.Adapter adapter = new CategoryRecyclerAdapter(getContext(),null);


        categoryRV.setAdapter(adapter);


        /// Ads Grid
        grid = view.findViewById(R.id.mainAdsGV);
        grid.setOnScrollListener(new StaggeredGridView.OnScrollListener() {
            @Override
            public void onTop() {

            }

            @Override
            public void onScroll() {

            }

            @Override
            public void onBottom() {

            }
        });
        loadAds();
        return view;

    }

    void loadAds()
    {
        try{
            JSONArray array = new JSONArray(ADS_MIN_JSON);
            for(int i = 0; i < array.length(); i++)
            {
                JSONObject singleAd = array.getJSONObject(i);
                HashMap<String,String> data = new HashMap<>();
                data.put(PRE_CITY,singleAd.getString(PRE_CITY));
                data.put(PRE_PRICE,singleAd.getString(PRE_PRICE));
                data.put(PRE_TITLE,singleAd.getString(PRE_TITLE));
                data.put(PRE_DATE,"20 Dec");
                AdCard card = new AdCard(getContext(),data);
                grid.addItem(card);
            }
        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
