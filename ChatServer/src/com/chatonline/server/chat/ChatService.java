package com.chatonline.server.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ChatService extends Thread{

    private static final int PORT = 9876;
    private boolean configed = false;

    private ServerSocketChannel ssc;
    private Selector selector;

    public void configuration() throws IOException {
        ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(PORT));
        ssc.configureBlocking(false);
        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        configed = true;
    }

    @Override
    public void run() {
        if (configed == false){
            throw new IllegalStateException();
        }
    }
}
