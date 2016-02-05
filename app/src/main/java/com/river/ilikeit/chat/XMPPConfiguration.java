package com.river.ilikeit.chat;

public class XMPPConfiguration {

    //  The hostname to connect to
    // For google talk, set to talk.google.com
//    public static final String SERVER = "xmpp.l.google.com";
    public static final String SERVER = "talk.google.com";

    // The service name to use; if unset, it will use the value of xmpp.server
    // For google talk, set to gmail.com
    public static final String SERVICENAME = "gmail.com";

    // The port to connect to; defaults to 5222 if unset
    public static final int PORT = 5222;

    // Whether to enable TLS encryption; defaults to false if unset
    public static final boolean TLSEnabled = true;

    // Whether to enable SASL authentication; defaults to true if unset
    public static final boolean SASLEnabled = true;

    // Whether to allow self-signed certificates; defaults to false if unset
    public static final boolean selfSignedCertificateEnabled = true;

    // The java XMPP libraries store certificates in a trust store.  It defines
    // the password used to protect the trust store; defaults to changeit if unset
    // xmpp.truststorePassword = changeit

    // Enable debugging; extremely high-volume, you probably should not turn this on
    public static final boolean debuggerEnabled = false;

    // The username to connect as; just the username - doesn't require the @domain
    // xmpp.user   = (username)

    // The user's password
    // xmpp.pass   = (insert password)
}
