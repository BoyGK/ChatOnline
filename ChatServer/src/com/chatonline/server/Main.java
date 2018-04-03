package com.chatonline.server;

import com.chatonline.server.chat.ChatService;
import com.chatonline.server.rpc.RpcService;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Main {
    private static ChatService cService;
    private static RpcService rService;
    public static void main(String[] args){
        cService = new ChatService();
        cService.start();
        rService = new RpcService(cService);
        rService.start();
    }
}
