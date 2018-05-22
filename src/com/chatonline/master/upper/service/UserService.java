package com.chatonline.master.upper.service;

import com.chatonline.master.rpc.RpcClient;
import com.chatonline.master.rpcinterface.IChatManager;
import com.chatonline.master.upper.bean.Mes;
import com.chatonline.master.upper.bean.Room;
import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.dao.Dao;
import com.chatonline.master.upper.dao.UserDao;
import com.chatonline.master.upper.util.CreateTokenFactory;
import com.chatonline.server.chat.ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public boolean login(String user, String pass) {
        Dao dao = new UserDao();
        User u = (User) dao.query(user);
        if (u != null && u.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }

    public Mes forResult() {
        String token = CreateTokenFactory.getRandomString();
        saveToken(token);

        RpcClient client = new RpcClient();
        List<ChatRoom> rooms = (List<ChatRoom>) client.rpc("127.0.0.1", 6789, "getChatRoomsInfo",
                IChatManager.class.getName(), null,null);
        Mes mes = new Mes("Success", 1, token);
        mes.setTargetIp("127.0.0.1");
        List<Room> roomList = new ArrayList<>();
        for (ChatRoom room : rooms) {
            Room room1 = new Room();
            room1.setHouseId(room.getRoomNo());
            room1.setNowCount(room.getAllUser().size());
            room1.setMaxCount(room.MAX_USER);
            roomList.add(room1);
        }
        mes.setRooms(roomList);
        return mes;
    }

    private void saveToken(String token) {

    }
}
