package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerHallInfo;
import cn.ohyeah.ww.server.model.ServerRoomInfo;

import java.util.ArrayList;
import java.util.List;

public class GameStarter {
    public void configServer() {
        ServerHallInfo hallInfo = new ServerHallInfo(0, "hall1");
        ServerRoomInfo roomInfo = new ServerRoomInfo(0, "6人混战之六道轮回", 100, 6);
        roomInfo.setHall(hallInfo);
        List<ServerRoomInfo> rooms = new ArrayList<>();
        rooms.add(roomInfo);
        hallInfo.setRooms(rooms);
    }
}
