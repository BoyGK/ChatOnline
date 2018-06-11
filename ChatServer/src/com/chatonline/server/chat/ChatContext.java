package com.chatonline.server.chat;

import java.util.*;

public class ChatContext {
    private Map<String, ProcessHandle> handles = new HashMap<>();
    private List<ChatRoom> rooms = new ArrayList<>();
    private Integer from;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public List<ChatRoom> getRooms() {
        System.out.println(rooms.size());
        return rooms;
    }

    public void setRoomCount(int count, int startId) {
        from = startId;
        for (int i = 0; i < count; i++) {
            rooms.add(new ChatRoom(i + startId));
        }
        System.out.println(rooms.size());
    }

    public void registerHandle(String key, ProcessHandle handle) {
        handles.put(key, handle);
    }

    public ProcessHandle getHandle(String key) {
        return handles.get(key);
    }
}
