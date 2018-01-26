package com.olx.smartlife_solutions.olx_syria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.olx.smartlife_solutions.olx_syria.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    ArrayList<FavoriteItems> favoriteItemsArrayList;
    RelativeLayout loadingAndFailedParentRL;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        favoriteItemsArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            favoriteItemsArrayList.add(new FavoriteItems("", "Name "+i, "$$$", true));
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        loadingAndFailedParentRL = view.findViewById(R.id.loadingAndFailedRL);


        RecyclerView favRecyclerView = view.findViewById(R.id.favRecycler);
        favRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager favLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        favRecyclerView.setLayoutManager(favLayoutManager);
        RecyclerView.Adapter favAdapter = new FavforiteAdapter(getActivity().getBaseContext(), favoriteItemsArrayList);
        favRecyclerView.setAdapter(favAdapter);

        return view;
    }

}
