package com.chatonline.server.chat;

public class ForwardHandle implements ProcessHandle {
    @Override
    public boolean handle(ChatContext chatContext, User user) {
        System.out.println("recevied forward msg: " + user.getLastMsg());
        int roomid = user.getRoomId();
        System.out.println(roomid);
        ChatRoom chatRoom = chatContext.getRooms().get(roomid - chatContext.getFrom());
        chatRoom.forward(user);
        return true;
    }
}
