package com.chatonline.server.chat;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatService {

    public static void main(String[] args) throws IOException {
        Context context = new Context();
        configContext(context);
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new ChatServiceHandle(context));


        acceptor.bind(new InetSocketAddress(9876));

    }

    private static void configContext(Context context) {
        context.setRoomCount(5);

        ProcessHandle addHandle = new AddHandle();
        ProcessHandle forwardHandle = new ForwardHandle();
        ProcessHandle closeHandle = new CloseHandle();

        context.registerHandle(ProcessHandle.ADD_KEY, addHandle);
        context.registerHandle(ProcessHandle.FORWARD_KEY, forwardHandle);
        context.registerHandle(ProcessHandle.CLOSE_KEY, closeHandle);
    }
}
