package com.river.ilikeit;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private String username;
    private String password;

    private static AppPreferences instance;

    public static synchronized AppPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new AppPreferences(context.getApplicationContext());
        }
        return instance;
    }

    public AppPreferences(Context context) {
        sharedPref = context.getSharedPreferences(Constants.SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public String getUsername() {
        username = sharedPref.getString(Constants.USERNAME, null);
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        editor.putString(Constants.USERNAME, username);
        editor.commit();
    }

    public String getPassword() {
        password = sharedPref.getString(Constants.PASSWORD, null);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        editor.putString(Constants.PASSWORD, password);
        editor.commit();
    }
}
