package com.river.ilikeit.chat.listener.roster;

import android.util.Log;

import org.jivesoftware.smack.packet.Presence;

import java.util.Collection;

public class RosterListener implements org.jivesoftware.smack.roster.RosterListener {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void entriesAdded(Collection<String> addresses) {
        Log.d(TAG, "entriesAdded: " + addresses.size());
        for (String address : addresses) {
            Log.d(TAG, address);
        }
    }

    @Override
    public void entriesUpdated(Collection<String> addresses) {
        Log.d(TAG, "entriesUpdated: " + addresses.size());
        for (String address : addresses) {
            Log.d(TAG, address);
        }
    }

    @Override
    public void entriesDeleted(Collection<String> addresses) {
        Log.d(TAG, "entriesDeleted: " + addresses.size());
        for (String address : addresses) {
            Log.d(TAG, address);
        }
    }

    @Override
    public void presenceChanged(Presence presence) {
        Log.d(TAG, "presenceChanged: " + presence);
    }
}
