package com.chatonline.server.rpc;

import com.chatonline.server.chat.ChatContext;
import com.chatonline.server.bean.SendBody;
import com.chatonline.server.chat.ChatRoom;
import com.chatonline.server.rpcinterface.IChatManager;

import java.util.List;

public class ChatManagerImpl implements IChatManager {

    private ChatContext chatContext;

    public ChatManagerImpl(ChatContext chatContext) {
        this.chatContext = chatContext;
    }

    @Override
    public List<ChatRoom> getChatRoomsInfo() {
        return chatContext.getRooms();
    }
}
