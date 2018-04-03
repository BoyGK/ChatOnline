package com.chatonline.server.rpc;

import com.chatonline.server.chat.ChatService;
import com.chatonline.server.rpcinterface.IChatManager;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RpcService extends Thread {
    private ChatService cService;
    private RpcContext context;

    public RpcService(ChatService cService) {
        this.cService = cService;
        context = new RpcContext();
        configContext();
    }

    private void configContext() {
        context.register(IChatManager.class,
                new ChatManagerImpl(cService.getChatContext()));
    }

    @Override
    public void run() {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new RpcHandle(context));
        try {
            acceptor.bind(new InetSocketAddress(6789));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
