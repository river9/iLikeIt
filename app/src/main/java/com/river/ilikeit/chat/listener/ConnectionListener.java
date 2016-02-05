package com.river.ilikeit.chat.listener;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.river.ilikeit.Constants;
import com.river.ilikeit.main.BroadcastHelper;

import org.jivesoftware.smack.XMPPConnection;

public class ConnectionListener implements org.jivesoftware.smack.ConnectionListener {
    private final String TAG = this.getClass().getSimpleName();

    private Context context;
    private Bundle data;

    public ConnectionListener(Context context) {
        this.context = context;
    }

    @Override
    public void connected(XMPPConnection connection) {
        Log.d(TAG, "connected");
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_CONNECTED);
        broadcastConnectionState(data);
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        Log.d(TAG, "authenticated resumed: " + resumed);
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_AUTHENTICATED);
        data.putBoolean(Constants.CONNECTION_RESUMED, resumed);
        broadcastConnectionState(data);
    }

    @Override
    public void connectionClosed() {
        Log.d(TAG, "connectionClosed");
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_CONNECTION_CLOSED);
        broadcastConnectionState(data);
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        Log.d(TAG, "connectionClosedOnError: " + e.toString());
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_CONNECTION_CLOSED_ON_ERROR);
        data.putString(Constants.CONNECTION_ERROR, e.toString());
        broadcastConnectionState(data);
    }

    @Override
    public void reconnectionSuccessful() {
        Log.d(TAG, "reconnectionSuccessful");
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_RECONNECTION_SUCCESSFUL);
        broadcastConnectionState(data);
    }

    @Override
    public void reconnectingIn(int seconds) {
        Log.d(TAG, "reconnectingIn: " + seconds);
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_RECONNECTING_IN);
        data.putInt(Constants.CONNECTION_RECONNECTING, seconds);
        broadcastConnectionState(data);
    }

    @Override
    public void reconnectionFailed(Exception e) {
        Log.d(TAG, "reconnectionFailed: " + e.toString());
        data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_RECONNECTION_FAILED);
        data.putString(Constants.CONNECTION_ERROR, e.toString());
        broadcastConnectionState(data);
    }
    
    private void broadcastConnectionState(Bundle data) {
        BroadcastHelper.getInstance().sendBroadcast(context, Constants.BRC_XMPP_CONNECTION_STATE, data);
    }
}
