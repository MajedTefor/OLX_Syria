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

/**
 * Created by Majed-PC on 12/8/2017.
 */

public class HomeFragment extends Fragment {

    RecyclerView categoryRV;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);

        categoryRV = view.findViewById(R.id.categoryRV);
        categoryRV.setHasFixedSize(true);


        LinearLayoutManager categoryRVLayout = new LinearLayoutManager(getContext());
        categoryRVLayout.setOrientation(LinearLayoutManager.HORIZONTAL);


        categoryRV.setLayoutManager(categoryRVLayout);

        RecyclerView.Adapter adapter = new CategoryRecyclerAdapter(getContext(),null);


        categoryRV.setAdapter(adapter);


        StaggeredGridView grid = view.findViewById(R.id.mainAdsGV);
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



        AdCard card = new AdCard();
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);
        grid.addItem(card);



        return view;

    }
}
