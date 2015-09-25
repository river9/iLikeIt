package com.river.ilikeit.chat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.river.ilikeit.AppPreferences;
import com.river.ilikeit.Constants;
import com.river.ilikeit.chat.listener.ConnectionListener;
import com.river.ilikeit.chat.listener.chat.ChatManagerListener;
import com.river.ilikeit.chat.listener.muc.InvitationListener;
import com.river.ilikeit.chat.listener.muc.PresenceListener;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import java.io.IOException;
import java.util.Collection;

public class ChatService extends Service {
    private final String TAG = ChatService.class.getSimpleName();

    private static XMPPTCPConnection connection;

    private ChatManager chatManager;
    private MultiUserChatManager mucManager;
    private XMPPTCPConnectionConfiguration.Builder configBuilder;
    private PresenceListener presenceListener;
    private static Roster roster;

    private boolean isInit = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        if (!isInit) {
            init();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        try {
            Log.i(TAG, "onStartCommand connection.isConnected(): " + connection.isConnected());
            if (!connection.isConnected()) {
                connection.connect();
            }
            Log.i(TAG, "onStartCommand connection.isAuthenticated(): " + connection.isAuthenticated());
            if (!connection.isAuthenticated()) {
                String username = AppPreferences.getInstance(this).getUsername();
                String password = AppPreferences.getInstance(this).getPassword();
                connection.login(username, password);
            }
        } catch (SmackException | IOException | XMPPException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void init() {
        Log.i(TAG, "init");
        configBuilder = XMPPTCPConnectionConfiguration.builder()
                .setHost(Constants.HOST)
                .setPort(Constants.PORT)
                .setServiceName(Constants.SERVICE);

        connection = new XMPPTCPConnection(configBuilder.build());
        connection.addConnectionListener(new ConnectionListener(this));

        chatManager = ChatManager.getInstanceFor(connection);
        chatManager.addChatListener(new ChatManagerListener());

        mucManager = MultiUserChatManager.getInstanceFor(connection);
        mucManager.addInvitationListener(new InvitationListener());

        roster = Roster.getInstanceFor(connection);

        isInit = true;
    }

    public void logout() {
        Presence presence = new Presence(Presence.Type.unavailable);
        connection.disconnect();
    }

    public static void getRosters() {
        Collection<RosterEntry> entries = roster.getEntries();
        for (RosterEntry entry : entries) {
            System.out.println(entry);
        }
    }
}
