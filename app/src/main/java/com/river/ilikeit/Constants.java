package com.river.ilikeit;

public class Constants {

    public static final String SHARED_PREFS_FILE_NAME = "my_app_shared_prefs";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String ARG_SECTION_NUMBER = "section_number";

    public static final String BRC_XMPP_CONNECTION_STATE = "BRC_XMPP_CONNECTION_STATE";

    public static final String CONNECTION_STATE = "CONNECTION_STATE";
    public static final String CONNECTION_RESUMED = "CONNECTION_RESUMED";
    public static final String CONNECTION_ERROR = "CONNECTION_ERROR";
    public static final String CONNECTION_RECONNECTING = "CONNECTION_RECONNECTING";

    public static final int STATE_CONNECTED = 1;
    public static final int STATE_AUTHENTICATED = 2;
    public static final int STATE_CONNECTION_CLOSED = 3;
    public static final int STATE_CONNECTION_CLOSED_ON_ERROR = 4;
    public static final int STATE_RECONNECTING_IN = 5;
    public static final int STATE_RECONNECTION_SUCCESSFUL = 6;
    public static final int STATE_RECONNECTION_FAILED = 7;
    public static final int STATE_CONNECT_ERROR = 8;
    public static final int STATE_AUTHENTICATE_ERROR = 9;
}
