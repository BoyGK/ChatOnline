package com.chatonline.server.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandle extends IoHandlerAdapter{

    private String msg;
    public ClientHandle(String msg) {
        this.msg = msg;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.write(msg);
    }
}
