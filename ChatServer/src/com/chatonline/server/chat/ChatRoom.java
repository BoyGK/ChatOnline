package com.chatonline.server.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements Serializable{

    public static final int MAX_USER = 30;
    private int roomNo = -1;

    private List<User> allUser = new ArrayList<>();


    public ChatRoom(int id) {
        this.roomNo = id;
    }

    public boolean isFull() {
        return allUser.size() >= MAX_USER ? true : false;
    }

    public void registerUser(User user) {
        allUser.add(user);
    }
    public void removeUser(User user){
        allUser.remove(user);
    }

    public void forward(User from) {
        System.out.println("chat room " + roomNo +" begin forward");
        for (User user : allUser) {
            if (user!=from) {
                System.out.println("to : " + user.getRealSession().getId());
                user.getRealSession().write(from.getLastMsg());
            }
        }
    }

    public void setRoomNo(int no) {
        roomNo = no;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public List<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<User> allUser) {
        this.allUser = allUser;
    }
}
