package com.river.ilikeit.chat.listener.roster;

import android.util.Log;

import org.jivesoftware.smack.roster.Roster;

public class RosterLoadedListener implements org.jivesoftware.smack.roster.RosterLoadedListener {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onRosterLoaded(Roster roster) {
        Log.d(TAG, "onRosterLoaded: " + roster.getEntryCount());
        Log.d(TAG, "onRosterLoaded: " + roster.getGroupCount());
    }
}
