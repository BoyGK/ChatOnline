package com.chatonline.server.test;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory())
        );

        connector.setHandler(new ClientHandle());

        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 9876));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String cmd = cin.nextLine();
            String[] split = cmd.split("-");
            com.chatonline.server.chat.SendBody body = new com.chatonline.server.chat.SendBody();
            body.setKey(split[0]);
            body.setData(split[1]);
            session.write(body);
            System.out.println("send finish");
        }
    }

}
