package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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

public class AdCard extends StaggeredGridViewItem implements StaticStrings, API_URLs{

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
        CardView parentCard = card.findViewById(R.id.parentCardCV);
        Picasso.with(context).load(MAIN_Thumbnail_IMG_AD + data.get(PRE_GUID)).placeholder(R.drawable.no_img).into(img);
        title.setText(data.get(PRE_TITLE));
        dateAndLocation.setText(data.get(PRE_DATE) + "     " + data.get(PRE_CITY));
        price.setText(data.get(PRE_PRICE));
        parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPreview = new Intent(context,PreviewAddActivity.class);
                goToPreview.putExtra(PRE_GUID,data.get(PRE_GUID));
                context.startActivity(goToPreview);
            }
        });
        return card;
    }

    @Override
    public int getViewHeight(LayoutInflater inflater, ViewGroup parent) {

        LinearLayout item_containerFrameLayout = card.findViewById(R.id.container);
        item_containerFrameLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return item_containerFrameLayout.getMeasuredHeight();

    }
}
