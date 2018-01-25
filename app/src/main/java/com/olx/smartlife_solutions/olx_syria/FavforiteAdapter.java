package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Crazy ITer on 1/25/2018.
 */

public class FavforiteAdapter extends RecyclerView.Adapter<FavforiteAdapter.ViewHolder> {
    Context context;
    ArrayList<FavoriteItems> favoriteItemsArrayList;

    public FavforiteAdapter(Context context, ArrayList<FavoriteItems> favoriteItemsArrayList) {
        this.context = context;
        this.favoriteItemsArrayList = favoriteItemsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item_view, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(favoriteItemsArrayList.get(position).getName());
        holder.price.setText(favoriteItemsArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return favoriteItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout favLayoutItem;
        TextView name, price;
        ImageView favItemImage, favCancel;
        public ViewHolder(View itemView) {
            super(itemView);
            favLayoutItem = itemView.findViewById(R.id.favLayoutItem);
            name = itemView.findViewById(R.id.favNameItem);
            price = itemView.findViewById(R.id.favPriceItem);
            favItemImage = itemView.findViewById(R.id.favImageItem);
            favCancel = itemView.findViewById(R.id.favCancel);
        }
    }
}
