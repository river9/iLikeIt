package com.river.ilikeit.chat.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.river.ilikeit.MainActivity;
import com.river.ilikeit.chat.ChatService;

import org.jivesoftware.smack.XMPPConnection;

public class ConnectionListener implements org.jivesoftware.smack.ConnectionListener {
    private final String TAG = this.getClass().getSimpleName();

    private Context context;

    public ConnectionListener(Context context) {
        this.context = context;
    }

    @Override
    public void connected(XMPPConnection connection) {
        Log.i(TAG, "connected");
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        Log.i(TAG, "authenticated resumed: " + resumed);
        Log.i(TAG, "authenticated context: " + (context != null));

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ChatService.getRosters();
    }

    @Override
    public void connectionClosed() {
        Log.i(TAG, "connectionClosed");
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.i(TAG, "connectionClosedOnError: " + e.toString());
    }

    @Override
    public void reconnectionSuccessful() {
        Log.i(TAG, "reconnectionSuccessful");
    }

    @Override
    public void reconnectingIn(int seconds) {
        Log.i(TAG, "reconnectingIn: " + seconds);
    }

    @Override
    public void reconnectionFailed(Exception e) {
        Log.i(TAG, "reconnectionFailed: " + e.toString());
    }
}
