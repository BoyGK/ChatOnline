package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.bean.ResultModel;
import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class RepassAction extends ActionSupport{

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String username;
    private String new_password;
    private String old_password;
    private String token;


    @Override
    public String execute() throws Exception {
        UserService service = new UserService();
        ResultModel re = service.repass(getUsername(),getOld_password(),getToken(),getNew_password());
        if (re != null) {
            Out.writer().print(new Gson().toJson(re));
        } else {
            Out.writer().print(new Gson().toJson(new ResultModel("该用户不存在", 0)));
        }
        return null;
    }
}
