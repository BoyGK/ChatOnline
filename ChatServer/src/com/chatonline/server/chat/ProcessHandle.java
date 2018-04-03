package com.chatonline.server.chat;

public interface ProcessHandle {
    String ADD_KEY = "add";
    String FORWARD_KEY = "forward";
    String CLOSE_KEY = "close";
    boolean handle(ChatContext chatContext, User user);

}
