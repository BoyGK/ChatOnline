package com.chatonline.master.upper.bean;

import java.util.List;

public class LoginModel {
    private String msg;
    private Integer state;
    private String token;
    private String nickname;
    private List<Room> rooms;

    public LoginModel(String msg, Integer state, String token, String nickname, List<Room> rooms) {
        this.msg = msg;
        this.state = state;
        this.token = token;
        this.nickname = nickname;
        this.rooms = rooms;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
