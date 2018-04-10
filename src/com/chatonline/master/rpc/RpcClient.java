package com.chatonline.master.rpc;

import com.chatonline.server.bean.RpcConfig;
import com.chatonline.server.chat.ChatRoom;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.List;

public class RpcClient {

    Object object = null;

    public synchronized Object rpc(String ip, int port, String methodName, String interfaceName, Object... args) {
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler(new RpcClient().new MyHandle(new Call() {
            @Override
            public void call(Object o) {
                object = o;
            }
        }));
        ConnectFuture future = connector.connect(new InetSocketAddress(ip, port));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        RpcConfig config = new RpcConfig();
        config.setClazz(interfaceName);
//        Method method = null;
//        try {
//            method = IChatManager.class.getMethod(methodName);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        config.setMethod(methodName);
        config.setArguments(args);
        session.write(config);
        while (object == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    private class MyHandle extends IoHandlerAdapter {
        Call call;

        public MyHandle(Call call) {
            this.call = call;
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            call.call(message);
        }
    }

    private interface Call {
        void call(Object o);
    }

}
