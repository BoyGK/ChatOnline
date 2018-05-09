package com.chatonline.server.chat;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatService extends Thread{

    private ChatContext chatContext;
    public ChatService(){
        chatContext = new ChatContext();
        configContext(chatContext);
    }
    public void run()  {

        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new ChatServiceHandle(chatContext));

        try {
            acceptor.bind(new InetSocketAddress(9876));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void configContext(ChatContext chatContext) {
        chatContext.setRoomCount(5,0);

        ProcessHandle addHandle = new AddHandle();
        ProcessHandle forwardHandle = new ForwardHandle();
        ProcessHandle closeHandle = new CloseHandle();

        chatContext.registerHandle(ProcessHandle.ADD_KEY, addHandle);
        chatContext.registerHandle(ProcessHandle.FORWARD_KEY, forwardHandle);
        chatContext.registerHandle(ProcessHandle.CLOSE_KEY, closeHandle);
    }

    public ChatContext getChatContext() {
        return chatContext;
    }

    public void setChatContext(ChatContext chatContext) {
        this.chatContext = chatContext;
    }
}
