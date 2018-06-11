package com.chatonline.server.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandle extends IoHandlerAdapter {


    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message.toString());
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("send ");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        System.out.println("异常发生");
    }
}
