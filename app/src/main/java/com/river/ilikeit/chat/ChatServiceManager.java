package com.river.ilikeit.chat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ChatServiceManager {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private Boolean started = false;
    private RemoteServiceConnection connection;
    private IRemoteChatService remoteService;

    private static ChatServiceManager instance;

    public static synchronized ChatServiceManager getInstance(Context context) {
        if (instance == null) {
            instance = new ChatServiceManager(context);
        }
        return instance;
    }

    public ChatServiceManager(Context context) {
        this.context = context;
    }

    public void startService(){
        Log.d(TAG, "startService(): " + started);
        if (!started) {
            Intent intent = new Intent(context, ChatService.class);
            context.startService(intent);
            started = true;
            Log.d(TAG, "startService()");
        }
    }

    public void bindService() {
        if(connection == null) {
            connection = new RemoteServiceConnection();
            Intent intent = new Intent(context, ChatService.class);
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "bindService()");
        } else {
            Log.d(TAG, "Cannot bind - service already bound");
        }
    }

    public void releaseService() {
        if(connection != null) {
            context.unbindService(connection);
            connection = null;
            Log.d(TAG, "releaseService()");
        } else {
            Log.d(TAG, "Cannot unbind - service not bound");
        }
    }

    public void stopService() {
        if (!started) {
            Log.d(TAG, "Service not yet started");
        } else {
            Intent intent = new Intent(context, ChatService.class);
            context.stopService(intent);
            started = false;
            Log.d(TAG, "stopService()");
        }
    }

    public IRemoteChatService getRemoteService() {
        Log.d(TAG, "getRemoteService: " + remoteService);
//        if (remoteService == null) {
//            bindService();
//        }
        return remoteService;
    }

    private class RemoteServiceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName className, IBinder boundService) {
            remoteService = IRemoteChatService.Stub.asInterface((IBinder) boundService);
            Log.d(TAG, "onServiceConnected()");
            try {
                remoteService.connect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            remoteService = null;
            Log.d(TAG, "onServiceDisconnected" );
        }
    };
}
