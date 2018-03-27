package com.chatonline.server.test;

import org.apache.mina.core.session.IoSession;

public class User {
    private IoSession session;
    private int charRoomNo;
    private String token;

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getCharRoomNo() {
        return charRoomNo;
    }

    public void setCharRoomNo(int charRoomNo) {
        this.charRoomNo = charRoomNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
