package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Majed-PC on 12/14/2017.
 */

public class DetailsListViewAdapter extends BaseAdapter {

    private LayoutInflater inf;
    private List<HashMap<String,String>> list;

    DetailsListViewAdapter(Context con, List<HashMap<String,String>> list)
    {
        inf = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            view = inf.inflate(R.layout.details_list_item,null);

            TextView propTV = view.findViewById(R.id.propTV);
            TextView valTV = view.findViewById(R.id.valTV);

            propTV.setText(list.get(position).get("prop"));
            valTV.setText(list.get(position).get("val"));


        }
        return view;
    }
}
