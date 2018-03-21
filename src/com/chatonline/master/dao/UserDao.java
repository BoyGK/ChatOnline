package com.chatonline.master.dao;

import com.chatonline.master.bean.User;
import com.chatonline.master.util.CreateUserFactory;

/**
 * 模拟用户数据库操作
 */
public class UserDao {

    public User query(String username) {
        User user = null;
        String password = CreateUserFactory.getUser().get(username);
        if (password != null) {
            user = new User(username, password);
        }
        return user;
    }

}
