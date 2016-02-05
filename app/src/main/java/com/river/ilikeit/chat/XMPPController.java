package com.river.ilikeit.chat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.river.ilikeit.AppPreferences;
import com.river.ilikeit.Constants;
import com.river.ilikeit.chat.listener.ConnectionListener;
import com.river.ilikeit.chat.listener.chat.ChatManagerListener;
import com.river.ilikeit.chat.listener.muc.InvitationListener;
import com.river.ilikeit.chat.listener.muc.PresenceListener;
import com.river.ilikeit.chat.listener.roster.RosterListener;
import com.river.ilikeit.chat.listener.roster.RosterLoadedListener;
import com.river.ilikeit.main.BroadcastHelper;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.ping.PingManager;

import java.io.IOException;
import java.util.Collection;

public class XMPPController {
    private final String TAG = this.getClass().getSimpleName();

    private Context context;

    private XMPPTCPConnection connection;
    private XMPPTCPConnectionConfiguration xmppConfig;
    private ChatManager chatManager;
    private MultiUserChatManager mucManager;
    private PingManager pingManager;
    private PresenceListener presenceListener;
    private Roster roster;
    private String username;
    private String password;

    private static XMPPController instance;

    public XMPPController(Context context) {
        super();
        this.context = context;
        initConnection();
    }

    public static synchronized XMPPController getInstance(Context context) {
        if (instance == null) {
            instance = new XMPPController(context);
        }
        return instance;
    }

    private void initConnection() {
        Log.d(TAG, "init");
        XMPPTCPConnectionConfiguration.Builder configBuilder =
                XMPPTCPConnectionConfiguration.builder()
                .setHost(XMPPConfiguration.SERVER)
                .setPort(XMPPConfiguration.PORT)
                .setServiceName(XMPPConfiguration.SERVICENAME)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                .setConnectTimeout(3000)
                .setSendPresence(true)
                .setDebuggerEnabled(true);

        xmppConfig = configBuilder.build();
        connection = new XMPPTCPConnection(xmppConfig);
        connection.addConnectionListener(new ConnectionListener(context));
        initExtensions();
    }

    private void initExtensions() {
        chatManager = ChatManager.getInstanceFor(connection);
        chatManager.addChatListener(new ChatManagerListener());

        mucManager = MultiUserChatManager.getInstanceFor(connection);
        mucManager.addInvitationListener(new InvitationListener());

        roster = Roster.getInstanceFor(connection);
        roster.addRosterListener(new RosterListener());
        roster.addRosterLoadedListener(new RosterLoadedListener());

        pingManager = PingManager.getInstanceFor(connection);
        pingManager.setPingInterval(30);
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public void connect() {
        Log.d(TAG, "connect connection.isConnected(): " + connection.isConnected());
        if (!connection.isConnected()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.connect();
                    } catch (SmackException | IOException | XMPPException e) {
                        Log.e(TAG, "threadConnect error: " + e);
                        broadcastConnectError(e.getMessage());
                    }
                }
            }).start();
        }
    }

    public void login() {
        Log.d(TAG, "login connection.isAuthenticated(): " + connection.isAuthenticated());
        if (connection.isAuthenticated()) {
            Bundle data = new Bundle();
            data.putInt(Constants.CONNECTION_STATE, Constants.STATE_AUTHENTICATED);
            data.putBoolean(Constants.CONNECTION_RESUMED, false);
            BroadcastHelper.getInstance().sendBroadcast(context, Constants.BRC_XMPP_CONNECTION_STATE, data);
        } else {
            username = AppPreferences.getInstance(context).getUsername();
            password = AppPreferences.getInstance(context).getPassword();
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connection.login(username, password);
                        } catch (XMPPException | SmackException | IOException e) {
                            Log.e(TAG, "threadLogin error: " + e);
                            broadcastLoginError(e.getMessage());
                        }
                    }
                }).start();
            } else {
                broadcastLoginError(null);
            }
        }
    }

    public void logout() {
        AppPreferences.getInstance(context).setUsername(null);
        AppPreferences.getInstance(context).setPassword(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Presence presence = new Presence(Presence.Type.unavailable);
                try {
                    connection.disconnect(presence);
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getRosters() {
        Collection<RosterEntry> entries = roster.getEntries();
        Log.d(TAG, "entries size: " + entries.size());
        for (RosterEntry entry : entries) {
            Log.d(TAG, entry.toString());
        }
    }

    public void sendMessage(String to, String content) {
        Chat chat = chatManager.getThreadChat(to);
        if (chat == null) {
            chat = chatManager.createChat(to);
        }
        Log.d(TAG, "sendMessage chat: " + chat);
        try {
            chat.sendMessage(content);
        } catch (SmackException.NotConnectedException e) {
            Log.e(TAG, "sendMessage error: " + e);
        }
    }

    private void broadcastConnectError(String error) {
        Bundle data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_CONNECT_ERROR);
        data.putString(Constants.CONNECTION_ERROR, error);
        BroadcastHelper.getInstance().sendBroadcast(context, Constants.BRC_XMPP_CONNECTION_STATE, data);
    }

    private void broadcastLoginError(String error) {
        Bundle data = new Bundle();
        data.putInt(Constants.CONNECTION_STATE, Constants.STATE_AUTHENTICATE_ERROR);
        data.putString(Constants.CONNECTION_ERROR, error);
        BroadcastHelper.getInstance().sendBroadcast(context, Constants.BRC_XMPP_CONNECTION_STATE, data);
    }
}
