package com.chatonline.server.rpc;

import com.chatonline.server.bean.RpcConfig;
import com.chatonline.server.bean.SendBody;
import com.chatonline.server.chat.ChatRoom;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.lang.reflect.Method;
import java.util.List;

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
        String name = rpcConfig.getClazz().replaceAll("master", "server");
        System.out.println(name);
        Class c = Class.forName(name);
        Object o = context.getImpl(c);
        Method method = o.getClass().getMethod(rpcConfig.getMethod(), rpcConfig.getParameterTypes());
        Object resObj = null;
        if (rpcConfig.getParameterTypes() == null) {
            resObj = method.invoke(o);
        } else {
            resObj = method.invoke(o, rpcConfig.getArguments());
        }
        System.out.println(resObj == null ? true : false);
        session.write(resObj);

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
