package com.chatonline.server.rpc;

import com.chatonline.server.bean.RpcConfig;
import org.apache.mina.core.future.WriteFuture;
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
    public void messageReceived(IoSession session, Object message) {
        try {
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
            System.out.println("send finish");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("send");
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        System.out.println("有tmd异常");
        cause.printStackTrace();
    }
}
