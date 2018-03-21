package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.bean.Mes;
import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.UserService;
import com.chatonline.master.upper.util.CreateTokenFactory;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

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
        if (service.login(getUsername(), getPassword())) {
            Out.writer().print(new Gson().toJson(new Mes("", 1, CreateTokenFactory.getRandomString())));
        }else {
            Out.writer().print(new Gson().toJson(new Mes("用户名或密码错误!!!", 0, "")));
        }
        return null;
    }
}
