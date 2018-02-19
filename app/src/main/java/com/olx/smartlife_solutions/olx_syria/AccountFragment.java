package com.olx.smartlife_solutions.olx_syria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olx.smartlife_solutions.olx_syria.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    RelativeLayout relativeHide;
    FloatingActionButton fabEdit, fabEditName, fabEditPassword, fabEditPhone;
    LinearLayout editLayout, newNameLayout, newPhoneLayout, newPasswordLayout;
    EditText newName, newPhone, newPassword;
    TextView undoNewName, undoNewPhone, undoNewPassword;
    RecyclerView myAdsRecyclerView;
    ArrayList<AdItems> adItemsArrayList;
    Button save, cancel;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        // Main
        save = view.findViewById(R.id.saveNewInfo);
        cancel = view.findViewById(R.id.cancelNewInfo);
        editLayout = view.findViewById(R.id.editLayout);
        newNameLayout = view.findViewById(R.id.newNameLayout);
        newPhoneLayout = view.findViewById(R.id.newPhoneLayout);
        newPasswordLayout = view.findViewById(R.id.newPasswordLayout);
        newName = view.findViewById(R.id.newName);
        newPhone = view.findViewById(R.id.newPhone);
        newPassword = view.findViewById(R.id.newPassword);
        undoNewName = view.findViewById(R.id.undoNewName);
        undoNewPhone = view.findViewById(R.id.undoNewPhone);
        undoNewPassword = view.findViewById(R.id.undoNewPassword);
        //
        relativeHide = view.findViewById(R.id.relative_hide);
        fabEdit = view.findViewById(R.id.fabEditInformation);
        fabEditName = view.findViewById(R.id.fabEditName);
        fabEditPhone = view.findViewById(R.id.fabEditPhone);
        fabEditPassword = view.findViewById(R.id.fabEditPassword);

        relativeHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViewRelative();
            }
        });
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViewRelative();
            }
        });
        fabEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViewRelative();
                newNameLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.VISIBLE);
                myAdsRecyclerView.setVisibility(View.GONE);
            }
        });
        fabEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViewRelative();
                newPasswordLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.VISIBLE);
                myAdsRecyclerView.setVisibility(View.GONE);
            }
        });
        fabEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleViewRelative();
                newPhoneLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.VISIBLE);
                myAdsRecyclerView.setVisibility(View.GONE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Changes Saved", Toast.LENGTH_SHORT).show();
                editLayout.setVisibility(View.GONE);
                myAdsRecyclerView.setVisibility(View.VISIBLE);
                newNameLayout.setVisibility(View.GONE);
                newPhoneLayout.setVisibility(View.GONE);
                newPasswordLayout.setVisibility(View.GONE);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Changes Don't Saved", Toast.LENGTH_SHORT).show();
                editLayout.setVisibility(View.GONE);
                myAdsRecyclerView.setVisibility(View.VISIBLE);
                newNameLayout.setVisibility(View.GONE);
                newPhoneLayout.setVisibility(View.GONE);
                newPasswordLayout.setVisibility(View.GONE);
            }
        });

        undoListeners();
        setMyAdsRecycler(view);
        return view;
    }

    private void setMyAdsRecycler(View view) {
        adItemsArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            adItemsArrayList.add(new AdItems("", "My Ad "+i));
        }

        myAdsRecyclerView = view.findViewById(R.id.myAdsRecycler);
        myAdsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager myAdsLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        myAdsRecyclerView.setLayoutManager(myAdsLayoutManager);
        RecyclerView.Adapter myAdsAdapter = new MyAdsAdapter(getActivity().getBaseContext(), adItemsArrayList);
        myAdsRecyclerView.setAdapter(myAdsAdapter);
    }

    public void undoListeners() {
        undoNewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newName.setText("");
                newNameLayout.setVisibility(View.GONE);
            }
        });
        undoNewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPhone.setText("");
                newPhoneLayout.setVisibility(View.GONE);
            }
        });
        undoNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassword.setText("");
                newPasswordLayout.setVisibility(View.GONE);
            }
        });
    }

    public void toggleViewRelative() {
        if (relativeHide.getVisibility() == View.GONE) {
            relativeHide.setVisibility(View.VISIBLE);
        } else {
            relativeHide.setVisibility(View.GONE);
        }
    }

}
