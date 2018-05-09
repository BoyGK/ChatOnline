package com.chatonline.master.upper.bean;

/**
 * 用户实体
 */
public class User {

    private String uesranme;
    private String password;

    public User(String uesranme, String password) {
        this.uesranme = uesranme;
        this.password = password;
    }

    public String getUesranme() {
        return uesranme;
    }

    public void setUesranme(String uesranme) {
        this.uesranme = uesranme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
