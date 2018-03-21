package com.chatonline.master;

import com.chatonline.master.upper.util.CreateUserFactory;

public class Main {

    public static void main(String... args){
        System.out.println(CreateUserFactory.getUser().get("zhangsan"));
    }
}
