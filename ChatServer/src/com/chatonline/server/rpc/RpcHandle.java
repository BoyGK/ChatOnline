package com.chatonline.server.rpc;

import com.chatonline.server.bean.RpcConfig;
import com.chatonline.server.chat.SendBody;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.lang.reflect.Method;

public class RpcHandle extends IoHandlerAdapter {

    private RpcContext context;

    public RpcHandle(RpcContext context) {
        this.context = context;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        System.out.println("rpc handle received msg");
        RpcConfig rpcConfig = (RpcConfig) message;

        Class c = Class.forName(rpcConfig.getClazz());
        Object o = context.getImpl(c);
        Method method = o.getClass().getMethod(rpcConfig.getMethod());
        SendBody body = (SendBody)method.invoke(o,rpcConfig.getArguments());
        System.out.println(body==null?true:false);
        session.write(body);

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }
}
