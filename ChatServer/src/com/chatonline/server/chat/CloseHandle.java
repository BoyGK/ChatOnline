package com.chatonline.server.chat;

public class CloseHandle implements ProcessHandle {

    @Override
    public boolean handle(ChatContext chatContext, User user) {
        int roomid = user.getRoomId();
        chatContext.getRooms().get(roomid - chatContext.getFrom()).removeUser(user);
        user.getRealSession().closeNow();
        System.out.println(user.getRealSession().getId() + " removed");
        return true;
    }
}
