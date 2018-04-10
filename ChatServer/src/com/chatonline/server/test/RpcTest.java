package com.chatonline.server.test;

import com.chatonline.server.bean.RpcConfig;
import com.chatonline.server.bean.SendBody;
import com.chatonline.server.rpcinterface.IChatManager;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class RpcTest {

    public static void main(String[] args) {
//        IoConnector connector = new NioSocketConnector();
//        connector.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
//        MyHandle handle = new RpcTest().new MyHandle();
//        connector.setHandler(handle);
//        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 6789));
//        future.awaitUninterruptibly();
//        IoSession session = future.getSession();
//        session.write(new RpcConfig());
        test();
    }

    private static void test(){
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler(new RpcTest().new MyHandle());
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 6789));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        RpcConfig config = new RpcConfig();
        config.setClazz(IChatManager.class.getName());
        Method method = null;
        try {
            method = IChatManager.class.getMethod("getChatRoomsInfo");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        config.setMethod(method.getName());
        config.setArguments(null);
        System.out.println("before send");
        session.write(config);
        System.out.println("after send");
    }

    public class MyHandle extends IoHandlerAdapter{

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
            System.out.println("cr");
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            System.out.println("re");
            SendBody sendBody = (SendBody) message;
            System.out.println("sendBody "+ sendBody);
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
            System.out.println("send !!!");
        }
    }

}
