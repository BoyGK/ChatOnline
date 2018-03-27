package com.chatonline.server.chat;

public class ForwardHandle implements ProcessHandle {
    @Override
    public boolean handle(Context context, User user) {
        int roomid = user.getRoomId();
        System.out.println(roomid);
        ChatRoom chatRoom = context.getRooms().get(roomid);
        chatRoom.forward(user);
        return true;
    }
}
