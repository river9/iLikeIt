// IChatAidlInterface.aidl
package com.river.ilikeit.chat;

// Declare any non-default types here with import statements

interface IRemoteChatService {

    void connect();
    void login();
    void logout();
    void sendMessage(String to, String content);
    void getRosters();
    boolean isConnected();
}
