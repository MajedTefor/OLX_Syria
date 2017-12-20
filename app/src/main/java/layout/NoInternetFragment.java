package layout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.olx.smartlife_solutions.olx_syria.MainApp;
import com.olx.smartlife_solutions.olx_syria.R;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class NoInternetFragment extends android.app.Fragment {

    public NoInternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_no_internet, container, false);
        Button btnRetry = v.findViewById(R.id.btnRetryConnection);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainApp.checkInternet.isConnected()) {
                    MainApp.fragmentTransaction = getFragmentManager().beginTransaction();
                    MainApp.fragmentTransaction.hide(MainApp.noInternetFragment);
                    MainApp.fragmentTransaction.commit();
                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                }
            }
        });

        return v;
    }

}
