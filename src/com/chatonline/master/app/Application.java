package com.chatonline.master.app;

import com.chatonline.master.rpc.RpcClient;
import com.chatonline.master.rpcinterface.IChatManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Application extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        new Thread(() -> {
            RpcClient client = new RpcClient();
            client.rpc("183.175.12.154", 6789, "initChatRooms", IChatManager.class.getName(), 10, 1);
        }).start();

    }
}
