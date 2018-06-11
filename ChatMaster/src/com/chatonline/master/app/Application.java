package com.chatonline.master.app;

import com.chatonline.master.rpc.RpcClient;
import com.chatonline.master.rpcinterface.IChatManager;
import com.chatonline.master.util.ConfigReader;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Application extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        for(ConfigReader.Tar tar:ConfigReader.getServerConfig().getRoom_config()){
            new Thread(() -> {
                int to = tar.getTo();
                int from = tar.getFrom();
                RpcClient client = new RpcClient();
                client.rpc(tar.getTarget(), 6789, "initChatRooms",
                        IChatManager.class.getName(), new Class[]{Integer.class,Integer.class}, to - from, from);
        }).start();
        }
    }


}
