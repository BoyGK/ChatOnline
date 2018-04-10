package com.chatonline.master.rpcinterface;

import com.chatonline.server.chat.ChatRoom;

import java.util.List;

public interface IChatManager {

    List<ChatRoom> getChatRoomsInfo();

}
