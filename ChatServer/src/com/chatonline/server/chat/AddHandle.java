package com.chatonline.server.chat;

public class AddHandle implements ProcessHandle {

    @Override
    public boolean handle(Context context, User user) {
        int roomid = Integer.valueOf(user.getLastMsg());
        System.out.println(roomid);
        user.setRoomId(roomid);
        ChatRoom chatRoom = context.getRooms().get(roomid);
        if (!chatRoom.isFull()) {
            chatRoom.registerUser(user);
            return true;
        }
        return false;


    }
}
