package com.river.ilikeit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.river.ilikeit.chat.ChatServiceManager;
import com.river.ilikeit.main.BroadcastHelper;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    private RadioButton rb1;
    private AlertDialog errorDialog;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        registerBroadcastReceiver();
        rb1 = (RadioButton) findViewById(R.id.rb1);
        errorDialog = Utility.initErrorDialog(this, null);
        loadingDialog = Utility.initLoadingDialog(this);
        ChatServiceManager.getInstance(this).bindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
        ChatServiceManager.getInstance(this).releaseService();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                loadingDialog.show();
                String username = rb1.isChecked() ? "matthewhrobak@gmail.com" : "hoanggiang262@gmail.com";
                String password = rb1.isChecked() ? "hoanggiang262" : "HuynhGiang";
                Log.i(TAG, "login info: " + username + "/" + password);
                AppPreferences.getInstance(this).setUsername(username);
                AppPreferences.getInstance(this).setPassword(password);
                try {
                    if (ChatServiceManager.getInstance(this).getRemoteService().isConnected()) {
                        ChatServiceManager.getInstance(this).getRemoteService().login();
                    } else {
                        ChatServiceManager.getInstance(this).getRemoteService().connect();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void registerBroadcastReceiver() {
        BroadcastHelper.getInstance().registerReceiver(this, receiverXMPPConnectionState, Constants.BRC_XMPP_CONNECTION_STATE, null);
    }

    private void unregisterBroadcastReceiver() {
        BroadcastHelper.getInstance().unregisterReceiver(this, receiverXMPPConnectionState);
    }

    private BroadcastReceiver receiverXMPPConnectionState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadingDialog.dismiss();
            Bundle data = intent.getExtras();
            int state = data.getInt(Constants.CONNECTION_STATE);
            Log.d(TAG, "receiverXMPPConnectionState: " + state);
            switch (state) {
                case Constants.STATE_CONNECTED:
                    loadingDialog.show();
                    try {
                        ChatServiceManager.getInstance(context).getRemoteService().login();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        loadingDialog.dismiss();
                        errorDialog.setMessage(e.getMessage());
                        errorDialog.show();
                    }
                    break;
                case Constants.STATE_AUTHENTICATED:
                    callMainActivity();
                    break;
                case Constants.STATE_CONNECTION_CLOSED:
                    Toast.makeText(context, "CONNECTION_CLOSED", Toast.LENGTH_SHORT).show();
                    break;
                case Constants.STATE_AUTHENTICATE_ERROR:
                    String error = data.getString(Constants.CONNECTION_ERROR);
                    errorDialog.setMessage(error);
                    errorDialog.show();
                    break;
            }
        }
    };

    private void callMainActivity() {
        Intent newIntent = new Intent(this, MainActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent);
    }

}
