package com.chatonline.server.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Context {
    private Map<String, ProcessHandle> handles = new HashMap<>();
    private Vector<ChatRoom> rooms;

    public Vector<ChatRoom> getRooms() {
        return rooms;
    }

    public void setRoomCount(int count) {
        rooms = new Vector<>();
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
