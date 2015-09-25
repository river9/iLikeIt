package com.river.ilikeit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.river.ilikeit.chat.ChatService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final String username = AppPreferences.getInstance(this).getUsername();
        final String password = AppPreferences.getInstance(this).getPassword();
        if (username != null && password != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, ChatService.class);
                    Log.i("tag","start service: " + Utility.getInstance(SplashActivity.this).isServiceRunning(ChatService.class));
                    if (!Utility.getInstance(SplashActivity.this).isServiceRunning(ChatService.class)){
                        startService(intent);
                        Log.i("tag","start service");
                    }
                }
            }).start();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
