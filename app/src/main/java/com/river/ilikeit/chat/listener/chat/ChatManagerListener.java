package com.river.ilikeit.chat.listener.chat;

import android.util.Log;

import org.jivesoftware.smack.chat.Chat;

public class ChatManagerListener implements org.jivesoftware.smack.chat.ChatManagerListener {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void chatCreated(Chat chat, boolean createdLocally) {
        Log.i(TAG, "chatCreated createdLocally: " + createdLocally);
        if (chat.getListeners() == null) {
            chat.addMessageListener(new ChatMessageListener());
        }
    }
}
