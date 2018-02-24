package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

public class SelectedImagesRVAdapter extends RecyclerView.Adapter<SelectedImagesRVAdapter.ViewHolder>{

    Context context;
    List<Bitmap> imgs;
    LayoutInflater inflater;

    SelectedImagesRVAdapter(Context context, List<Bitmap> imgs){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imgs = imgs;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.selected_img_item,parent,false);

        return new SelectedImagesRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.contentImgIV.setImageBitmap(imgs.get(position));
        holder.delImgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgs.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return imgs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView contentImgIV;
        ImageButton delImgIB;
        ViewHolder(View itemView) {
            super(itemView);
            contentImgIV = itemView.findViewById(R.id.contentImgIV);
            delImgIB = itemView.findViewById(R.id.delImgIB);

        }
    }
}
