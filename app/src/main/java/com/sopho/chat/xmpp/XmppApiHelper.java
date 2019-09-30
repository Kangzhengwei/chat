package com.sopho.chat.xmpp;

import java.util.HashMap;
import java.util.List;

public class XmppApiHelper {

    private final XmppApi xmppApi;

    public XmppApiHelper(XmppApi xmppApi) {
        this.xmppApi = xmppApi;
    }

    public boolean login(String name, String pwd) {
        return xmppApi.login(name, pwd);
    }

    public boolean connect() {
        return xmppApi.connect();
    }

    public List<HashMap<String, String>> searchUser(String userName) {
        return xmppApi.searchUsers(userName);
    }

    public boolean addFriend(String userName, String name) {
        return xmppApi.addFriend(userName, name);
    }

}
