package com.river.ilikeit;

public class Constants {

    public static final String SHARED_PREFS_FILE_NAME = "my_app_shared_prefs";

    public static final String ACCESS_TOKEN = "AecaPzoHfORIHsPcbvrvnAeio2akFASl_yFyUhZCfLKhoAAEegAAAAA";
    public static final String URL_BOARD = "https://api.pinterest.com/v1/boards/280560320475343984/pins/?access_token=" + ACCESS_TOKEN;
    public static final String URL_TRAVEL = "https://api.pinterest.com/v3/pidgets/boards/highquality/travel/pins/";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String ARG_SECTION_NUMBER = "section_number";

    public static final String BRC_ROSTER_LOADED = "BRC_ROSTER_LOADED";
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

    public static final String CONTACTS = "CONTACTS";
    public static final String JID = "jid";
    public static final String NAME = "name";
}
