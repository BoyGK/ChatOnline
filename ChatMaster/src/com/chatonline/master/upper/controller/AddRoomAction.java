package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.bean.ResultModel;
import com.chatonline.master.upper.bean.RoomModel;
import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.RoomService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class AddRoomAction extends ActionSupport {

    private Integer houseid;
    private String username;
    private String token;

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String execute() throws Exception {
        RoomService service = new RoomService();
        RoomModel re = service.isLogin(getHouseid(), getUsername(), getToken());
        if (re != null) {
            Out.writer().print(new Gson().toJson(re));
        } else {
            Out.writer().print(new Gson().toJson(new ResultModel("", 0)));
        }
        return null;
    }
}
