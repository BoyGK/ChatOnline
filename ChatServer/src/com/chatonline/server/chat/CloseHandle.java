package com.chatonline.server.chat;

public class CloseHandle implements ProcessHandle {

    @Override
    public boolean handle(Context context, User user) {
        int roomid = user.getRoomId();
        context.getRooms().get(roomid).removeUser(user);
        user.getRealSession().closeNow();
        System.out.println(user.getRealSession().getId() + " removed");
        return true;
    }
}
