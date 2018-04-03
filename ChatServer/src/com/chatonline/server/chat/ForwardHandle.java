package com.chatonline.server.chat;

public class ForwardHandle implements ProcessHandle {
    @Override
    public boolean handle(ChatContext chatContext, User user) {
        int roomid = user.getRoomId();
        System.out.println(roomid);
        ChatRoom chatRoom = chatContext.getRooms().get(roomid);
        chatRoom.forward(user);
        return true;
    }
}
