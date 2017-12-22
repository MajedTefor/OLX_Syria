package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Majed-PC on 12/7/2017.
 */

public class AdCard extends StaggeredGridViewItem implements StaticStrings{

    private View card;

    HashMap<String,String> data;
    Context context;

    public AdCard(Context context ,HashMap<String,String> data)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        card = inflater.inflate(R.layout.ad_card,null);
        TextView title = card.findViewById(R.id.titleTV);
        TextView dateAndLocation = card.findViewById(R.id.dateAndLocationTV);
        TextView price = card.findViewById(R.id.priceTV);
        ImageView img = card.findViewById(R.id.cardImgIV);
        Picasso.with(context).load("http://www.tallshipsfalmouth.co.uk/wp-content/uploads/2012/11/Mercedes-tall-ship2-e1354287088569.jpg").into(img);
        title.setText(data.get(PRE_TITLE));
        dateAndLocation.setText(data.get(PRE_DATE) + "     " + data.get(PRE_CITY));
        price.setText(data.get(PRE_PRICE));
        return card;
    }

    @Override
    public int getViewHeight(LayoutInflater inflater, ViewGroup parent) {

        LinearLayout item_containerFrameLayout = card.findViewById(R.id.container);
        item_containerFrameLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return item_containerFrameLayout.getMeasuredHeight();

    }
}
