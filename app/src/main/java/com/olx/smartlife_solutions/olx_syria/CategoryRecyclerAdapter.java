package com.olx.smartlife_solutions.olx_syria;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Majed-PC on 12/9/2017.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> implements API_URLs{

    private LayoutInflater inflater;
    private List<CategoriesModal.CategoryModalItem> items;
    private Context context;
    private String guid;
    CategoriesModal categoriesModal;
    String categoriesJson = "";

    CategoryRecyclerAdapter(Context context,String guid, String json)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.guid = guid;
        categoriesJson = json;
        categoriesModal = new CategoriesModal(context, json );
        items = new ArrayList<>();
        if(guid == null)
        {
            items = categoriesModal.getAllParents();
        }
        else{
            items = categoriesModal.getAllChilds(guid);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CategoriesModal.CategoryModalItem singleItem = items.get(position);
        holder.catName.setText(singleItem.getName());
        final String itemGuid = singleItem.getGuid();
        Picasso.with(context).load(CATS_IMAGES_URL + singleItem.getImagePath()).placeholder(R.mipmap.ic_category_blank_img).into(holder.catImg);
        holder.catContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleItem.isHasChilds())
                {
                    RecyclerView categoryRV = ((Activity)context).findViewById(R.id.categoryRV);
                    RecyclerView.Adapter adapter = new CategoryRecyclerAdapter(context,itemGuid,categoriesJson);
                    categoryRV.setAdapter(adapter);
                }
                ((TextView)((Activity)context).findViewById(R.id.catIdET)).setText(itemGuid);

            }
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView catName,catGuid;
        ImageView catImg;
        LinearLayout catContainer;

        ViewHolder(View itemView) {
            super(itemView);

            catContainer = itemView.findViewById(R.id.catItemContainer);
            catImg = itemView.findViewById(R.id.catImg);
            catName = itemView.findViewById(R.id.catName);
            catGuid = itemView.findViewById(R.id.catGuid);

        }
    }

}
