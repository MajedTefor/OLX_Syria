package com.olx.smartlife_solutions.olx_syria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Majed-PC on 12/7/2017.
 */

public class AdCard extends StaggeredGridViewItem {

    private View card;

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        card = inflater.inflate(R.layout.ad_card,null);

        return card;
    }

    @Override
    public int getViewHeight(LayoutInflater inflater, ViewGroup parent) {

        LinearLayout item_containerFrameLayout = card.findViewById(R.id.container);
        item_containerFrameLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return item_containerFrameLayout.getMeasuredHeight();

    }
}
