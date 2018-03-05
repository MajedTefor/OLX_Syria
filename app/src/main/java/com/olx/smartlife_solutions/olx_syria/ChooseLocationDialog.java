package com.olx.smartlife_solutions.olx_syria;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;


class ChooseLocationDialog extends Dialog implements View.OnClickListener, API_URLs{

    private RecyclerView dataRV;
    private String catJson = null;
    private RequestQueue requestQueue;
    private CategoriesModal modal;
    private LoadingAndFailedView loadingAndFailedView;
    HashMap<String,String> selectedCatsHM;
    int cursorPosition = 1;

    ChooseLocationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_data_dialog);
        getWindow().setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT);
        dataRV = findViewById(R.id.dataRV);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(getContext());

        selectedCatsHM = new HashMap<>();

        selectedCatsHM.put("id1","");
        selectedCatsHM.put("name1","");

        selectedCatsHM.put("id2","");
        selectedCatsHM.put("name2","");

        selectedCatsHM.put("id3","");
        selectedCatsHM.put("name3","");

        dataRV.setHasFixedSize(true);
        LinearLayoutManager categoryRVLayout = new LinearLayoutManager(getContext());
        categoryRVLayout.setOrientation(LinearLayoutManager.VERTICAL);
        dataRV.setLayoutManager(categoryRVLayout);
        loadingAndFailedView = new LoadingAndFailedView();
        loadingAndFailedView.initialize(findViewById(R.id.loadingAndFailedParent));

        loadCategories(null);

    }

    HashMap<String, String> getSelectedCatsHM()
    {
        return selectedCatsHM;
    }

    String getSelectedCatsString()
    {
        String val = "";
        for(int i = 1; i < 4; i++)
        {
            String v = selectedCatsHM.get("name"+i);
            if(!v.isEmpty())
            {
                if(i != 1) val += ", ";
                val += v;
            }
        }
        return val;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cancelBtn:
                dismiss();
                break;
        }
    }

    void loadCategories(final String guid)
    {

        if(catJson == null)
        {
            final StringRequest getCategoriesJson = new StringRequest(Request.Method.GET, CATEGORIES_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            catJson = s;
                          //  Toast.makeText(getContext(),catJson,Toast.LENGTH_LONG).show();
                            modal = new CategoriesModal(getContext(),catJson);
                            CategoriesDialogRecyclerAdapter adapter;
                            if(guid == null)
                            {
                                adapter = new CategoriesDialogRecyclerAdapter(modal.getAllParents());

                            }
                            else{
                                adapter = new CategoriesDialogRecyclerAdapter(modal.getAllChilds(guid));
                            }

                            dataRV.setAdapter(adapter);
                            dataRV.setVisibility(View.VISIBLE);
                            findViewById(R.id.loadingAndFailedParent).setVisibility(View.GONE);
                            loadingAndFailedView.hideLoadingAndFailedView();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getContext(),volleyError.getMessage(),Toast.LENGTH_LONG).show();
                            loadingAndFailedView.showFailedView();
                        }
                    }
            );
            requestQueue.add(getCategoriesJson);

        }
        else{
            CategoriesDialogRecyclerAdapter adapter;
            if(guid == null)
            {
                adapter = new CategoriesDialogRecyclerAdapter(modal.getAllParents());

            }
            else{
                adapter = new CategoriesDialogRecyclerAdapter(modal.getAllChilds(guid));
            }

            dataRV.setAdapter(adapter);
            loadingAndFailedView.hideLoadingAndFailedView();
        }



    }

    class CategoriesDialogRecyclerAdapter extends RecyclerView.Adapter<CategoriesDialogRecyclerAdapter.ViewHolder>{

        LayoutInflater inflater;
        List<CategoriesModal.CategoryModalItem> items;
        CategoriesDialogRecyclerAdapter(List<CategoriesModal.CategoryModalItem> items)
        {
            this.items = items;
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.category_location_dialog_item,parent,false);

            return new CategoriesDialogRecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final CategoriesModal.CategoryModalItem item = items.get(position);
            holder.textTV.setText(item.getName());
            Picasso.with(getContext()).load(item.getImagePath()).into(holder.iconIV);
            if(!item.isHasChilds()) holder.rightArrow.setVisibility(View.GONE);
            holder.itemParentCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCatsHM.put("id"+cursorPosition, item.getGuid()+"");
                    selectedCatsHM.put("name"+cursorPosition, item.getName()+"");
                    cursorPosition++;
                    if(item.isHasChilds())
                    {
                        loadCategories(item.getGuid());
                    }
                    else{
                        dismiss();
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            CardView itemParentCV;
            ImageView iconIV, rightArrow;
            TextView textTV;

            ViewHolder(View itemView) {
                super(itemView);
                itemParentCV = itemView.findViewById(R.id.itemParentCV);
                iconIV = itemView.findViewById(R.id.iconIV);
                textTV = itemView.findViewById(R.id.textTV);
                rightArrow = itemView.findViewById(R.id.rightArrowIV);
            }
        }
    }
}
