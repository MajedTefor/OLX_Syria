package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Crazy ITer on 1/26/2018.
 */

class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.ViewHolder> {
    ArrayList<AdItems> adItemsArrayList;
    Context context;
    public MyAdsAdapter(Context baseContext, ArrayList<AdItems> adItemsArrayList) {
        this.context = baseContext;
        this.adItemsArrayList = adItemsArrayList;
    }

    @Override
    public MyAdsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_ads_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdsAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(adItemsArrayList.get(position).getItemName());
        /** TODO add image Picasso
         * add on delete and edit click Listeners
         * add on layout click listener
          */
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "TODO delete Ad", Toast.LENGTH_SHORT).show();
            }
        });
        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "TODO edit Ad", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout adLayout;
        ImageView itemImage, deleteItem, editItem;
        TextView itemName;
        public ViewHolder(View itemView) {
            super(itemView);
            adLayout = itemView.findViewById(R.id.adLayout);
            itemImage = itemView.findViewById(R.id.adImage);
            itemName = itemView.findViewById(R.id.adName);
            deleteItem = itemView.findViewById(R.id.deleteAd);
            editItem = itemView.findViewById(R.id.editAd);
        }
    }
}
