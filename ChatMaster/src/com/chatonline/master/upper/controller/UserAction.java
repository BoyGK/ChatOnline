package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.bean.ResultModel;
import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

    private String username;
    private String token;
    private String new_name;

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

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    @Override
    public String execute() throws Exception {
        UserService service = new UserService();
        ResultModel re = service.change(getUsername(), getToken(), getNew_name());
        if (re != null) {
            Out.writer().print(new Gson().toJson(re));
        } else {
            Out.writer().print(new Gson().toJson(new ResultModel("该用户不存在", 0)));
        }
        return null;
    }
}
