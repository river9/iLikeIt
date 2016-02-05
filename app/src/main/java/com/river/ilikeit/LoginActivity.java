package com.river.ilikeit;

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

import com.river.ilikeit.chat.ChatServiceManager;
import com.river.ilikeit.main.BroadcastHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();

    private RadioButton rb1;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        registerBroadcastReceiver();
        loadingDialog = Utility.initLoadingDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: " + v.getId());
        switch (v.getId()) {
            case R.id.btLogin:
                login();
                break;
        }
    }

    private void login() {
        Log.i(TAG, "onClick btLogin");
        String username = rb1.isChecked() ? "matthewhrobak@gmail.com" : "hoanggiang262";
        String password = rb1.isChecked() ? "hoanggiang262" : "HuynhGiang";

        Log.i(this.getClass().getSimpleName(), username + "/" + password);
        try {
            loadingDialog.show();
            ChatServiceManager.getInstance(this).getRemoteService().login();
            AppPreferences.getInstance(this).setUsername(username);
            AppPreferences.getInstance(this).setPassword(password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void registerBroadcastReceiver() {
        BroadcastHelper.getInstance().registerReceiver(this, receiverXMPPAuthenticated, Constants.BRC_XMPP_CONNECTION_STATE, null);
    }

    private void unregisterBroadcastReceiver() {
        BroadcastHelper.getInstance().unregisterReceiver(this, receiverXMPPAuthenticated);
    }

    private BroadcastReceiver receiverXMPPAuthenticated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadingDialog.dismiss();
            Bundle data = intent.getExtras();
            int state = data.getInt(Constants.CONNECTION_STATE);
            Log.d(TAG, "receiverXMPPConnectionState: " + state);
            switch (state) {
                case Constants.STATE_CONNECTED:
                    break;
                case Constants.STATE_AUTHENTICATED:
                    callMainActivity();
                    break;
                case Constants.STATE_AUTHENTICATE_ERROR:
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
