package com.chatonline.server.rpc;

import com.chatonline.server.chat.ChatContext;
import com.chatonline.server.bean.SendBody;
import com.chatonline.server.chat.ChatRoom;
import com.chatonline.server.rpcinterface.IChatManager;
import sun.plugin2.main.server.JVMInstance;

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

    @Override
    public boolean initChatRooms(int count, int startId) {
        try {
            chatContext.setRoomCount(count, startId);
            System.out.println("config rooms from service");
            //JVMInstance
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
