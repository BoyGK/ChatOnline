package com.chatonline.server.chat;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends Thread{

    public static final int MAX_USER = 30;
    private int roomNo = -1;

    private List<User> allUser = new ArrayList<>();
    private Selector mSelector;

    public ChatRoom() throws IOException {
        mSelector = Selector.open();
    }

    /**
     * 把新用户加入到房间
     * @return 成功与否
     */
    public boolean addUser(User u){
        if (allUser.size()<MAX_USER){
            allUser.add(u);
            registerUser();
            return true;
        }
        return false;
    }

    private void registerUser() {

    }

    public void setRoomNo(int no){
        roomNo = no;
    }

    public int getRoomNo() {
        return roomNo;
    }

    @Override
    public void run() {

    }
}
