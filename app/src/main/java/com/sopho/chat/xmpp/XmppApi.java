package com.sopho.chat.xmpp;

import com.sopho.chat.util.LogUtils;
import com.sopho.chat.util.SPUtils;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.sasl.provided.SASLPlainMechanism;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.xdata.Form;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class XmppApi {

    private AbstractXMPPConnection connection;
    private String TAG = "XmppApi";
    private Timer tExit;
    private int loginTime = 2000;
    private String username = null;
    private String password = null;

    public XmppApi(AbstractXMPPConnection connection) {
        this.connection = connection;
    }

    /**
     * 建立连接
     *
     * @return
     */
    public boolean connect() {
        if (connection.isConnected())// 首先判断是否还连接着服务器，需要先断开
            return true;
        try {
            Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.manual);
            connection.connect();
        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            e.printStackTrace();
            LogUtils.e(TAG, "连接失败");
            return false;
        }
        return true;
    }

    /**
     * 断开连接
     */
    public void disConnect() {
        if (null != connection && connection.isConnected()) {
            new Thread() {
                public void run() {
                    connection.removeConnectionListener(connectionListener);
                    connection.disconnect();
                }
            }.start();
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @return
     */
    public boolean login(String name, String pwd) {
        if (connection == null)
            return false;
        try {
            username = name;
            password = pwd;
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
            SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
            SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
            connection.login(name, pwd);
            connection.addConnectionListener(connectionListener);
            int status = SPUtils.getInstance().getInt("status");
            setPresence(status);   // 更改在线状态
            LogUtils.i(TAG, name + "/" + pwd + "登录了");
            return true;
        } catch (XMPPException | IOException | SmackException | InterruptedException xe) {
            xe.printStackTrace();
            LogUtils.e(TAG, "登录失败");
        }
        return false;
    }

    /**
     * 修改密码
     *
     * @return true成功
     */
    public boolean changePassWord(String pwd) {
        if (connection == null || !connection.isConnected())
            return false;
        try {
            AccountManager.getInstance(connection).changePassword(pwd);
            return true;
        } catch (SmackException | InterruptedException | XMPPException.XMPPErrorException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加好友 无分组
     *
     * @param userName userName
     * @param name     name
     * @return boolean
     */
    public boolean addFriend(String userName, String name) {
        if (connection == null)
            return false;
        try {
            Roster.getInstanceFor(connection).createEntry(JidCreate.bareFrom(userName), name, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询用户
     *
     * @param userName userName
     * @return List<HashMap                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                               String                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                               String>>
     */
    public List<HashMap<String, String>> searchUsers(String userName) {
        if (connection == null)
            return null;
        HashMap<String, String> user;
        List<HashMap<String, String>> results = new ArrayList<>();
        try {
            //ProviderManager.addIQProvider("query", "jabber:iq:search", new UserSearch.Provider());
            UserSearchManager manager = new UserSearchManager(connection);
            Form searchForm = manager.getSearchForm(JidCreate.domainBareFrom("search." + connection.getXMPPServiceDomain())).createAnswerForm();
            searchForm.setAnswer("Username", true);
            searchForm.setAnswer("search", userName);
            ReportedData data = manager.getSearchResults(searchForm, JidCreate.domainBareFrom("search." + connection.getXMPPServiceDomain()));
            List<ReportedData.Row> rowList = data.getRows();
            for (ReportedData.Row row : rowList) {
                user = new HashMap<>();
                user.put("userAccount", row.getValues("Username").toString());
                user.put("userPhote", row.getValues("search").toString());
                results.add(user);
            }
        } catch (SmackException | InterruptedException | XmppStringprepException | XMPPException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 判断连接是否通过了身份验证
     * 即是否已登录
     *
     * @return
     */
    public boolean isAuthenticated() {
        return connection != null && connection.isConnected() && connection.isAuthenticated();
    }

    /**
     * 设置状态
     *
     * @param state
     */
    public void setPresence(int state) {
        if (connection == null)
            return;
        Presence presence;
        try {
            switch (state) {
                case 0:
                    presence = new Presence(Presence.Type.available);
                    connection.sendStanza(presence);
                    LogUtils.i(TAG, "设置在线");
                    break;
                case 1:
                    presence = new Presence(Presence.Type.available);
                    presence.setMode(Presence.Mode.chat);
                    connection.sendStanza(presence);
                    LogUtils.i(TAG, "设置Q我吧");
                    break;
                case 2:
                    presence = new Presence(Presence.Type.available);
                    presence.setMode(Presence.Mode.dnd);
                    connection.sendStanza(presence);
                    LogUtils.i(TAG, "设置忙碌");
                    break;
                case 3:
                    presence = new Presence(Presence.Type.available);
                    presence.setMode(Presence.Mode.away);
                    connection.sendStanza(presence);
                    LogUtils.i(TAG, "设置离开");
                    break;
                case 5:
                    presence = new Presence(Presence.Type.unavailable);
                    connection.sendStanza(presence);
                    LogUtils.i(TAG, "设置离线");
                    break;
                default:
                    break;
            }
        } catch (SmackException.NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ConnectionListener connectionListener = new ConnectionListener() {
        @Override
        public void connected(XMPPConnection connection) {
            LogUtils.i(TAG, "连接成功");
        }

        @Override
        public void authenticated(XMPPConnection connection, boolean resumed) {
            LogUtils.i(TAG, "authenticated");
        }

        @Override
        public void connectionClosed() {
            LogUtils.i(TAG, "连接关闭");
            disConnect();
            // 重连服务器
            tExit = new Timer();
            tExit.schedule(new TimeTask(), loginTime);
        }

        @Override
        public void connectionClosedOnError(Exception e) {
            LogUtils.i(TAG, "连接出错");
            if (e.getMessage().contains("conflict")) {
                LogUtils.i(TAG, "被挤掉了");
                disConnect();
            }
        }
    };

    private class TimeTask extends TimerTask {
        @Override
        public void run() {
            if (username != null && password != null) {
                try {
                    if (isAuthenticated()) {// 用户未登录
                        if (login(username, password)) {
                            LogUtils.i(TAG, "登录成功");
                        } else {
                            LogUtils.i(TAG, "重新登录");
                            tExit.schedule(new TimeTask(), loginTime);
                        }
                    }
                } catch (Exception e) {
                    LogUtils.i(TAG, "尝试登录,出现异常!");
                }
            }
        }
    }


}
