package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.bean.ResultModel;
import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        UserService service = new UserService();
        if (service.register(getUsername(), getPassword())) {
            Out.writer().print(new Gson().toJson(new ResultModel("注册成功", 1)));
        } else {
            Out.writer().print(new Gson().toJson(new ResultModel("注册重名", 0)));
        }
        return null;
    }
}
