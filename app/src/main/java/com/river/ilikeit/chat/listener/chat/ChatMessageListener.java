package com.river.ilikeit.chat.listener.chat;

import android.util.Log;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

public class ChatMessageListener implements org.jivesoftware.smack.chat.ChatMessageListener {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void processMessage(Chat chat, Message message) {
        Log.i(TAG, "processMessage: " + message.toString());
    }
}
