package com.chatonline.server.chat;

public class AddHandle implements ProcessHandle {

    @Override
    public boolean handle(ChatContext chatContext, User user) {
        int roomid = Integer.valueOf(user.getLastMsg());
        System.out.println(roomid);
        user.setRoomId(roomid);
        ChatRoom chatRoom = chatContext.getRooms().get(roomid - chatContext.getFrom());
        if (!chatRoom.isFull()) {
            chatRoom.registerUser(user);
            return true;
        }
        return false;


    }
}
