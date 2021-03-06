package com.chatonline.master.upper.bean;

public class ResultModel {

    private String msg;
    private Integer state;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResultModel(String msg, Integer state) {
        this.msg = msg;
        this.state = state;
    }

    public ResultModel(String msg, Integer state, String token) {
        this.msg = msg;
        this.state = state;
        this.token = token;
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
}
