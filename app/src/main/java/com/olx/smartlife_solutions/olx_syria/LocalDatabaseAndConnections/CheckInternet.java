package com.olx.smartlife_solutions.olx_syria.LocalDatabaseAndConnections;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Crazy ITer on 11/25/2017.
 */

public class CheckInternet {
    Context context;
    public CheckInternet(Context context) {
        this.context = context;
    }
    // Check if your device connect to Internet
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
