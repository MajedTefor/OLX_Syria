package com.olx.smartlife_solutions.olx_syria;

import android.app.AlertDialog;
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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Majed-PC on 12/8/2017.
 */

public class HomeFragment extends Fragment implements StaticStrings,API_URLs{

    RecyclerView categoryRV;
    StaggeredGridView grid;

    RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        requestQueue = Volley.newRequestQueue(getContext());

        /// Category Slider
        categoryRV = view.findViewById(R.id.categoryRV);
        categoryRV.setHasFixedSize(true);

        LinearLayoutManager categoryRVLayout = new LinearLayoutManager(getContext());
        categoryRVLayout.setOrientation(LinearLayoutManager.HORIZONTAL);


        categoryRV.setLayoutManager(categoryRVLayout);

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
        downloadCategoriesThenMainAds();
        return view;

    }

    void downloadCategoriesThenMainAds(){
        final StringRequest getCategoriesJson = new StringRequest(Request.Method.GET, CATEGORIES_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                   // Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    loadCategories(s);
                    downloadMainAds();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }
        );
        requestQueue.add(getCategoriesJson);
    }

    void downloadMainAds(){
        StringRequest getMainAdsJson = new StringRequest(Request.Method.GET, ADS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loadAds(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
        requestQueue.add(getMainAdsJson);
    }

    void loadAds(String json)
    {
        try{
            JSONArray array = new JSONArray(json);
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

    void loadCategories(String json)
    {
        RecyclerView.Adapter adapter = new CategoryRecyclerAdapter(getContext(),null ,json);

        categoryRV.setAdapter(adapter);
    }
}
