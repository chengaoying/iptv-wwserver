package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;

import java.util.List;

public class ClientTableInfo {
    private int tableId;
    private String tableName;
    private List<ClientRoleDesc> players;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ClientRoleDesc> getPlayers() {
        return players;
    }

    public void setPlayers(List<ClientRoleDesc> players) {
        this.players = players;
    }

    public void serialize(ByteBuffer buf) {

    }

    public void deserialize(ByteBuffer buf) {

    }
}
