package com.chatonline.master.upper.service;

import com.chatonline.master.rpc.RpcClient;
import com.chatonline.master.rpcinterface.IChatManager;
import com.chatonline.master.upper.bean.LoginModel;
import com.chatonline.master.upper.bean.Room;
import com.chatonline.master.upper.bean.RoomModel;
import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.dao.Dao;
import com.chatonline.master.upper.dao.UserDao;
import com.chatonline.master.upper.util.CreateTokenFactory;
import com.chatonline.master.util.ConfigReader;
import com.chatonline.server.chat.ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    public RoomModel isLogin(int h, String u, String t) {
        Dao dao = new UserDao();
        List<User> users = dao.query();
        dao.close();
        for (User user1 : users) {
            if (user1.getUsername().equals(u) && user1.getToken().equals(t)) {
                return forRe(user1, h);
            }
        }
        return null;
    }

    private RoomModel forRe(User user, int h) {
        String token = CreateTokenFactory.getRandomString();
        user.setToken(token);
        Dao dao = new UserDao();
        dao.updata(user);
        dao.close();

        ConfigReader.Tar tar = ConfigReader.loadConfig(h);
        RoomModel roomModel = new RoomModel();
        roomModel.setMsg("");
        roomModel.setRemote(tar.getTarget());
        roomModel.setPort(tar.getPort());
        roomModel.setState(1);
        roomModel.setToken(token);
        return roomModel;
    }

    public LoginModel reRooms() {
        RpcClient client = new RpcClient();
        List<Room> roomList = new ArrayList<>();
        for (ConfigReader.Tar tar : ConfigReader.getServerConfig().getRoom_config()) {
            List<ChatRoom> rooms = (List<ChatRoom>) client.rpc(tar.getTarget(), 6789, "getChatRoomsInfo",
                    IChatManager.class.getName(), null, null);

            for (ChatRoom room : rooms) {
                Room room1 = new Room();
                room1.setHouseId(room.getRoomNo());
                room1.setNowCount(room.getAllUser().size());
                room1.setMaxCount(room.MAX_USER);
                roomList.add(room1);
            }
        }
        return new LoginModel("", 1, "", "", roomList);
    }
}
