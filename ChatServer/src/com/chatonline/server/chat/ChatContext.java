package com.chatonline.server.chat;

import java.util.*;

public class ChatContext {
    private Map<String, ProcessHandle> handles = new HashMap<>();
    private List<ChatRoom> rooms;

    public List<ChatRoom> getRooms() {
        return rooms;
    }

    public void setRoomCount(int count) {
        rooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            rooms.add(new ChatRoom(i));
        }
    }

    public void registerHandle(String key, ProcessHandle handle) {
        handles.put(key, handle);
    }

    public ProcessHandle getHandle(String key){
        return handles.get(key);
    }
}
