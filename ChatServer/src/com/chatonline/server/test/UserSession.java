package com.chatonline.server.test;

import org.apache.mina.core.session.IoSession;

public class UserSession {

    private IoSession realSession;
    private String name;
    private Integer roomId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public UserSession(IoSession realSession) {
        this.realSession = realSession;
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
}
