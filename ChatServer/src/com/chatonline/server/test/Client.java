package com.chatonline.server.test;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){

        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue()))
        );

        connector.setHandler(new ClientHandle("hello\r\nhi"));

        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1",9876));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()){
            session.write(cin.next());
        }
    }

}
