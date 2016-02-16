package com.river.ilikeit.chat.listener.roster;

import android.content.Context;
import android.util.Log;

import com.river.ilikeit.AppPreferences;
import com.river.ilikeit.Constants;
import com.river.ilikeit.main.BroadcastHelper;

import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

public class RosterLoadedListener implements org.jivesoftware.smack.roster.RosterLoadedListener {
    private final String TAG = getClass().getSimpleName();

    private Context context;

    public RosterLoadedListener(Context context) {
        this.context = context;
    }

    @Override
    public void onRosterLoaded(Roster roster) {
        Log.d(TAG, "onRosterLoaded entry: " + roster.getEntryCount());
        Log.d(TAG, "onRosterLoaded group: " + roster.getGroupCount());

        JSONArray array = new JSONArray();
        Collection<RosterEntry> entries = roster.getEntries();
        for (RosterEntry entry : entries) {
            JSONObject obj = new JSONObject();
            try {
                obj.put(Constants.JID, entry.getUser());
                obj.put(Constants.NAME, entry.getName());
                array.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        AppPreferences.getInstance(context).setContacts(array.toString());
        BroadcastHelper.getInstance().sendBroadcast(context, Constants.BRC_ROSTER_LOADED, null);
    }
}
