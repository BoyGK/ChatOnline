package com.chatonline.master.upper.controller;

import com.chatonline.master.upper.service.Out;
import com.chatonline.master.upper.service.RoomService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class GetRoomAction extends ActionSupport {


    @Override
    public String execute() throws Exception {

        Out.writer().print(new Gson().toJson(new RoomService().reRooms()));

        return null;
    }
}
