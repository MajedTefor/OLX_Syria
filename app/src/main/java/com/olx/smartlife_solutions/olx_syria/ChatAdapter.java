package com.olx.smartlife_solutions.olx_syria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Crazy ITer on 1/26/2018.
 */

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ChatItems> chatItemsArrayList;
    public ChatAdapter(Context baseContext, ArrayList<ChatItems> chatItemsArrayList) {
        this.context = baseContext;
        this.chatItemsArrayList = chatItemsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Picasso.with(context).load("").placeholder(R.drawable.chat).into(holder.userChatImage);
        holder.userChatName.setText(chatItemsArrayList.get(position).getUserChatName());
        holder.userChatLastMessage.setText(chatItemsArrayList.get(position).getUserChatLastMessage());
    }

    @Override
    public int getItemCount() {
        return chatItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userChatImage;
        TextView userChatName, userChatLastMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            userChatImage = itemView.findViewById(R.id.userChatImage);
            userChatName = itemView.findViewById(R.id.userChatName);
            userChatLastMessage = itemView.findViewById(R.id.userChatLastMessage);
        }
    }
}
