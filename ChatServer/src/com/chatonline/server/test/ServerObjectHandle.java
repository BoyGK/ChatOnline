package com.chatonline.server.test;

import com.chatonline.server.chat.SendBody;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Vector;

public class ServerObjectHandle extends IoHandlerAdapter {
    private Vector<ChatRoom> rooms;
    public ServerObjectHandle(Vector<ChatRoom> rooms){
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

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("server session closed");
        super.sessionClosed(session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("get message");
        UserSession userSession = (UserSession) session.getAttribute("session");
        SendBody body = (SendBody) message;
        System.out.println("received message: " + body.getKey() + " " + body.getData());
        int id = Integer.valueOf(body.getData());
        switch (body.getKey()){
            case "add":
                userSession.setRoomId(id);
                rooms.get(id).addUser(userSession);
                break;
            case "message":
                rooms.get(userSession.getRoomId()).forward(body.getData());
        }
    }


}
