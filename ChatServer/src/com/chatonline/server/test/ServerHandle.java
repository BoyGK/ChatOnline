package com.chatonline.server.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Random;
import java.util.Vector;

public class ServerHandle extends IoHandlerAdapter{
    private Vector<ChatRoom> rooms;
    public ServerHandle(Vector<ChatRoom> rooms){
        this.rooms = rooms;
    }
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("server session created");
        UserSession userSession = new UserSession(session);
        session.setAttribute("session",userSession);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("server session opened");

        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("server session closed");
        super.sessionClosed(session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        UserSession userSession = (UserSession) session.getAttribute("session");
        String str = message.toString();
        System.out.println("received message: " + str);
        String[] splits = str.split("-");

        switch (splits[0]){
            case "add":
                int id = Integer.valueOf(splits[1]);
                userSession.setRoomId(id);
                rooms.get(id).addUser(userSession);
                System.out.println("add chat room :" + id);
                break;
            case "message":
                rooms.get(userSession.getRoomId()).forward(splits[1]);
                System.out.println("send message :" + splits[1]);
                break;
        }

        if (str.endsWith("quit")) {
            session.close(true);
            return;
        }


    }


}
