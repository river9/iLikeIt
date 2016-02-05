package com.river.ilikeit.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class BroadcastHelper {
    private final String TAG = this.getClass().getSimpleName();

    private static BroadcastHelper instance;

    public static synchronized BroadcastHelper getInstance() {
        if (instance == null) {
            instance = new BroadcastHelper();
        }
        return instance;
    }

    public BroadcastHelper() {
    }

    public void sendBroadcast(Context context, String intentType, Bundle data) {
        Intent intent = new Intent(intentType);
        if (data != null) {
            intent.putExtras(data);
        }
        try {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            Log.d(TAG, "[Broadcast] from: " + context.getClass().getName());
            Log.d(TAG, "[Broadcast] " + intent.toString());
            if (data != null) {
                Log.d(TAG, "[Broadcast] " + data.toString());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void registerReceiver(Context context, BroadcastReceiver receiver, String intentType, String actionType) {
        IntentFilter filter = new IntentFilter(intentType);
        if (actionType != null) {
            filter.addAction(actionType);
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }
}
