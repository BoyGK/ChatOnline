package com.chatonline.server.chat;

import java.nio.channels.SocketChannel;

public class User {
    private SocketChannel mSocketChannel;
    private String nickName;
    private int id;
    private String token;

    public SocketChannel getmSocketChannel() {
        return mSocketChannel;
    }

    public void setmSocketChannel(SocketChannel mSocketChannel) {
        this.mSocketChannel = mSocketChannel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
