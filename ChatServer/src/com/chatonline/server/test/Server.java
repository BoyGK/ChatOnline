package com.chatonline.server.test;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Vector;

public class Server {

    public static void main(String[] args) throws IOException {
        Vector<ChatRoom> rooms = new Vector<>(5);
        rooms.add(new ChatRoom());
        rooms.add(new ChatRoom());
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

//        acceptor.getFilterChain().addLast("codec",
//                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
//                        LineDelimiter.WINDOWS.getValue(),
//                        LineDelimiter.WINDOWS.getValue()))
//        );
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new ServerObjectHandle(rooms));


        acceptor.bind(new InetSocketAddress(9876));

    }
}
