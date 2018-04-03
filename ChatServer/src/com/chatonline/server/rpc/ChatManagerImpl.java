package com.chatonline.server.rpc;

import com.chatonline.server.chat.ChatContext;
import com.chatonline.server.chat.SendBody;
import com.chatonline.server.rpcinterface.IChatManager;

public class ChatManagerImpl implements IChatManager {

    private ChatContext chatContext;

    public ChatManagerImpl(ChatContext chatContext) {
        this.chatContext = chatContext;
    }

    @Override
    public SendBody getChatRoomsInfo() {
        SendBody body = new SendBody();
        /**
         * (未实现)填充body
         */
        return body;
    }
}
