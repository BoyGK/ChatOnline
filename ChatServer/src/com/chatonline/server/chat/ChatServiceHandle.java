package com.chatonline.server.chat;

import com.chatonline.server.bean.SendBody;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ChatServiceHandle extends IoHandlerAdapter {

    private ChatContext mChatContext;

    public ChatServiceHandle(ChatContext chatContext) {
        mChatContext = chatContext;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("get conn");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("conn opened");
        User user = new User();
        user.setRealSession(session);
        session.setAttribute("user", user);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);

        mChatContext.getHandle(ProcessHandle.CLOSE_KEY).handle(mChatContext,
                (User) session.getAttribute("user"));

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("received");
        SendBody body = (SendBody) message;
        String key = body.getKey();
        User user = (User) session.getAttribute("user");
        user.setLastMsg(body.getData());
        System.out.println(mChatContext.getHandle(key) == null);
        ProcessHandle handle = mChatContext.getHandle(key);
        if (handle != null){
            handle.handle(mChatContext, user);
        }

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
