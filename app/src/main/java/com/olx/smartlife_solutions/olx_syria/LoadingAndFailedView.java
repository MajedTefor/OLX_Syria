package com.olx.smartlife_solutions.olx_syria;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadingAndFailedView  {

    private RelativeLayout loadingAndFailedParentRL, loadingRL;
    private LinearLayout failedLL;
    Button failedRetryBtn;
    private TextView statusCodeTV;
    private int statusCode = 0;

    void initialize(View view)
    {

        loadingAndFailedParentRL = view.findViewById(R.id.loadingAndFailedRL);
        loadingRL = view.findViewById(R.id.loadingViewRL);
        failedLL = view.findViewById(R.id.failedViewLL);
        failedRetryBtn = view.findViewById(R.id.failedRetryBtn);
        statusCodeTV = view.findViewById(R.id.statusCodeTV);
    }

    void setFailedRetryBtnClick(View.OnClickListener listener)
    {
        failedRetryBtn.setOnClickListener(listener);
    }

    void showFailedView()
    {
        statusCodeTV.setText(" " + statusCode);
        loadingAndFailedParentRL.setVisibility(View.VISIBLE);
        loadingRL.setVisibility(View.GONE);
        failedLL.setVisibility(View.VISIBLE);
    }

    void showLoadingView(){
        loadingAndFailedParentRL.setVisibility(View.VISIBLE);
        loadingRL.setVisibility(View.VISIBLE);
        failedLL.setVisibility(View.GONE);
    }

    void hideLoadingAndFailedView(){
        loadingAndFailedParentRL.setVisibility(View.GONE);
    }

}
