package com.river.ilikeit.chat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ChatService extends Service {
    private final String TAG = getClass().getSimpleName();

    private Context context = this;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind is called");
        return mBinder;
    }

    private IRemoteChatService.Stub mBinder = new IRemoteChatService.Stub() {
        @Override
        public void connect() throws RemoteException {
            XMPPController.getInstance(context).connect();
        }

        @Override
        public void login() throws RemoteException {
            XMPPController.getInstance(context).login();
        }

        @Override
        public void logout() throws RemoteException {
            XMPPController.getInstance(context).logout();
        }

        @Override
        public void sendMessage(String to, String content) throws RemoteException {
            XMPPController.getInstance(context).sendMessage(to, content);
        }

        @Override
        public void getRosters() throws RemoteException {
            XMPPController.getInstance(context).getRosters();
        }

        @Override
        public boolean isConnected() throws RemoteException {
            return XMPPController.getInstance(context).isConnected();
        }
    };
}
