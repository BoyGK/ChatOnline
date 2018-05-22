package com.chatonline.master.util;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class ConfigReader {

    public static Tar loadConfig(Integer HOUSEID) {
        FileInputStream inputStream = null;
        DataInputStream dataInputStream = null;
        try {
            inputStream = new FileInputStream(new File("room.json"));
            dataInputStream = new DataInputStream(inputStream);
            String json = dataInputStream.readUTF();
            Res rj = new Gson().fromJson(json, Res.class);
            for (Tar tar : rj.getRoom_config()) {
                if(HOUSEID.equals(tar.getHouse_id())){
                    return tar;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static class Res {
        private List<Tar> room_config;

        public List<Tar> getRoom_config() {
            return room_config;
        }

        public void setRoom_config(List<Tar> room_config) {
            this.room_config = room_config;
        }
    }

    public static class Tar {
        private Integer house_id;
        private String target;
        private String port;

        public Integer getHouse_id() {
            return house_id;
        }

        public void setHouse_id(Integer house_id) {
            this.house_id = house_id;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}
