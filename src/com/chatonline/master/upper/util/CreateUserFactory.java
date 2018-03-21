package com.chatonline.master.upper.util;

import java.util.HashMap;

/**
 * 生成虚拟用户
 */
public class CreateUserFactory {

    private CreateUserFactory(){

    }

    private static HashMap<String,String> users = new HashMap<>();

    static {
        users.put("zhangsan","123456");
        users.put("lisi","123456");
        users.put("wangwu","123456");
        users.put("shxy","123456");
        users.put("gk","123456");
        users.put("sory","123456");
        users.put("hello","123456");
        users.put("hiboy","123456");
        users.put("money","123456");
        users.put("oyes","123456");
    }

    public static HashMap<String, String> getUser(){
        return users;
    }

}
