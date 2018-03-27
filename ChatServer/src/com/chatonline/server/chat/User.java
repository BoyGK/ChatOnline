package com.chatonline.server.chat;

import org.apache.mina.core.session.IoSession;

public class User {
    private IoSession realSession;
    private String name;
    private String token;
    private int roomId;
    transient private String lastMsg;

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public IoSession getRealSession() {
        return realSession;
    }

    public void setRealSession(IoSession realSession) {
        this.realSession = realSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
