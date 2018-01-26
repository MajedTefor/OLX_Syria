package com.olx.smartlife_solutions.olx_syria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olx.smartlife_solutions.olx_syria.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    ArrayList<ChatItems> chatItemsArrayList;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        chatItemsArrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            chatItemsArrayList.add(new ChatItems("", "User "+i, "Hello World"));
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView chatRecyclerView = view.findViewById(R.id.chatRecycler);
        chatRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager chatLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        chatRecyclerView.setLayoutManager(chatLayoutManager);
        RecyclerView.Adapter chatAdapter = new ChatAdapter(getActivity().getBaseContext(), chatItemsArrayList);
        chatRecyclerView.setAdapter(chatAdapter);

        return view;
    }

}
