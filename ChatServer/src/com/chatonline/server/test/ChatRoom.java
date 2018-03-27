package com.chatonline.server.test;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    List<UserSession> sessions = new ArrayList<>();
    private int roomNo;

    void forward(String msg) {
        System.out.println("转发!!!");
        for (UserSession session : sessions) {
            System.out.println("转发给；" + session.getRealSession().getId());
            session.getRealSession().write(msg);
        }
    }

    public void addUser(UserSession session) {
        sessions.add(session);
    }
}
