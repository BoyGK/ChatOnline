package com.chatonline.master.upper.service;

import com.chatonline.master.rpc.RpcClient;
import com.chatonline.master.rpcinterface.IChatManager;
import com.chatonline.master.upper.bean.LoginModel;
import com.chatonline.master.upper.bean.ResultModel;
import com.chatonline.master.upper.bean.Room;
import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.dao.Dao;
import com.chatonline.master.upper.dao.UserDao;
import com.chatonline.master.upper.util.CreateTokenFactory;
import com.chatonline.server.chat.ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User login(String username, String password) {
        Dao dao = new UserDao();
        List<User> users = dao.query();
        dao.close();
        for (User user1 : users) {
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                return user1;
            }
        }
        return null;
    }

    public LoginModel forResult(User user) {
        RpcClient client = new RpcClient();
        List<ChatRoom> rooms = (List<ChatRoom>) client.rpc("127.0.0.1", 6789, "getChatRoomsInfo",
                IChatManager.class.getName(), null, null);
        List<Room> roomList = new ArrayList<>();
        for (ChatRoom room : rooms) {
            Room room1 = new Room();
            room1.setHouseId(room.getRoomNo());
            room1.setNowCount(room.getAllUser().size());
            room1.setMaxCount(room.MAX_USER);
            roomList.add(room1);
        }
        return new LoginModel("登陆成功", 1, user.getToken(), user.getNickname(), roomList);
    }

    public boolean register(String username, String password) {
        Dao dao = new UserDao();
        List<User> users = dao.query();
        for (User user1 : users) {
            if (user1.getUsername().equals(username)) {
                return false;
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(username);
        user.setToken(CreateTokenFactory.getRandomString());
        dao.save(user);
        dao.close();
        return true;
    }

    public ResultModel change(String username, String token, String new_name) {
        Dao dao = new UserDao();
        List<User> users = dao.query();
        dao.close();
        for (User user1 : users) {
            if (user1.getUsername().equals(username)) {
                if (!user1.getToken().equals(token))
                    return null;
                return changes(user1, new_name);
            }
        }
        return null;
    }

    private ResultModel changes(User user, String new_name) {
        String token = CreateTokenFactory.getRandomString();
        user.setToken(token);
        user.setNickname(new_name);
        Dao dao = new UserDao();
        dao.updata(user);
        dao.close();
        return new ResultModel("修改成功", 1, token);
    }
}
