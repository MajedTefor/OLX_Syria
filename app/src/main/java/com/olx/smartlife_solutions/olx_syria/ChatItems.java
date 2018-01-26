package com.olx.smartlife_solutions.olx_syria;

/**
 * Created by Crazy ITer on 1/26/2018.
 */

public class ChatItems {
    String userChatImageURL, userChatName, userChatLastMessage;

    public ChatItems(String userChatImageURL, String userChatName, String userChatLastMessage) {
        this.userChatImageURL = userChatImageURL;
        this.userChatName = userChatName;
        this.userChatLastMessage = userChatLastMessage;
    }

    public void setUserChatImageURL(String userChatImageURL) {
        this.userChatImageURL = userChatImageURL;
    }

    public void setUserChatName(String userChatName) {
        this.userChatName = userChatName;
    }

    public void setUserChatLastMessage(String userChatLastMessage) {
        this.userChatLastMessage = userChatLastMessage;
    }

    public String getUserChatImageURL() {

        return userChatImageURL;
    }

    public String getUserChatName() {
        return userChatName;
    }

    public String getUserChatLastMessage() {
        return userChatLastMessage;
    }
}
