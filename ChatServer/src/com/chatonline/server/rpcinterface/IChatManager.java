package com.chatonline.server.rpcinterface;

import com.chatonline.server.chat.ChatRoom;

import java.util.List;

public interface IChatManager {

    List<ChatRoom> getChatRoomsInfo();

    boolean initChatRooms(Integer count,Integer startId);
}
