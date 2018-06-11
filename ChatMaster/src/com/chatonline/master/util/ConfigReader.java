package com.chatonline.master.util;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class ConfigReader {

    public static Tar loadConfig(Integer HOUSEID) {
        BufferedReader reader = null;
        try {
            File file = new File("e:\\room.json");
            System.out.println(file.getPath());

            reader = new BufferedReader(new FileReader(file));
            StringBuilder json = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            Res rj = new Gson().fromJson(json.toString(), Res.class);
            for (Tar tar : rj.getRoom_config()) {
                if (HOUSEID >= tar.from && HOUSEID < tar.to) {
                    return tar;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
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
        private Integer from;
        private Integer to;
        private String target;
        private String port;

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
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
