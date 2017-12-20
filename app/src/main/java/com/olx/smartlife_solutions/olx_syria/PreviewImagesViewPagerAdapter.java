package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Majed-PC on 12/13/2017.
 */

public class PreviewImagesViewPagerAdapter extends PagerAdapter {

    List<Integer> imgs = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    PreviewImagesViewPagerAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imgs.add(R.drawable.draft);
        imgs.add(R.drawable.draft);
        imgs.add(R.drawable.draft);
        imgs.add(R.drawable.draft);
        imgs.add(R.drawable.draft);
    }


    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.preview_img_slider_item,null);
        ImageView img = view.findViewById(R.id.previewImgIV);
        img.setImageResource(imgs.get(position));

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
