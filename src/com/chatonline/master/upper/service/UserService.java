package com.chatonline.master.upper.service;

import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.dao.UserDao;

public class UserService {

    public boolean login(String user, String pass) {
        UserDao dao = new UserDao();
        User u = dao.query(user);
        if (u != null && u.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
}
