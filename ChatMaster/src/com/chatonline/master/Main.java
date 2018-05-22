package com.chatonline.master;

import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.dao.UserDao;
import com.chatonline.master.upper.util.CreateTokenFactory;
import com.chatonline.master.upper.util.CreateUserFactory;
import sun.security.krb5.internal.ktab.KeyTabInputStream;

import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String... args){
        UserDao dao = new UserDao();

        User user = new User();
        user.setId(2);
        user.setUsername("bgq");
        user.setPassword("654321");
        user.setNickname("GK");
        user.setToken(CreateTokenFactory.getRandomString());
        dao.updata(user);
        dao.close();
    }
}
