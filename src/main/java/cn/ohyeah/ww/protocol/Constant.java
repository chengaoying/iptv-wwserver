package cn.ohyeah.ww.protocol;

public class Constant {

    public static final byte PROTOCOL_VERSION = 2;
    public static final byte PROTOCOL_TAG_GAME_SERVER = 0X0F;

    public static enum ProtocolCmd {
        HALL,
        ROOM,
        TABLE,
        GAME
    };

    public static enum CmdHallUserdata {
        LOGIN,
        QUIT,
        QUERY_INFO
    };

    public static enum CmdRoomUserdata {
        LOGIN,
        QUIT,
        QUERY_INFO
    };

    public static enum CmdTableUserdata {
        LOGIN,
        QUIT,
        QUICK_JOIN,
        QUERY_INFO
    };

    public static enum CmdGameUserdata {
        PREPARE,
        QUIT,
        USE_PROP,
        ATTACK,
        END_ROUND,
        //QUERY_INFO
    };

    public static final String[][] PROTOCOL_CMDS_USERDATAS = {
        createNames(ProtocolCmd.values()),
        createNames(CmdHallUserdata.values()),
        createNames(CmdRoomUserdata.values()),
        createNames(CmdTableUserdata.values()),
        createNames(CmdGameUserdata.values())
    };

    private static final String createName(String name) {
        String[] fragments = name.split("_");
        StringBuilder nameBuilder = new StringBuilder(name.length());
        for (int i = 0; i < fragments.length; ++i) {
            String frag = fragments[i];
            if (i == 0) {
                nameBuilder.append(frag.toLowerCase());
            }
            else {
                nameBuilder.append(Character.toUpperCase(frag.charAt(0)));
                nameBuilder.append(frag.substring(1).toLowerCase());
            }
        }
        return nameBuilder.toString();
    }

    private static final String[] createNames(Enum[] values) {
        String[] strs = new String[values.length];
        for (int i = 0; i < strs.length; ++i) {
            strs[i] = createName(values[i].name());
        }
        return strs;
    }

    private static final void checkProtocolCmd(int cmd) {
        if (cmd < 0 || cmd > PROTOCOL_CMDS_USERDATAS[0].length-1) {
            throw new RuntimeException("协议标识GameServer不支持此协议命令, (cmd="+cmd+")");
        }
    }

    private static final void checkCmdUserdata(int cmd, int userdata) {
        if (userdata < 0 || userdata > PROTOCOL_CMDS_USERDATAS[cmd+1].length-1) {
            throw new RuntimeException("协议命令"+PROTOCOL_CMDS_USERDATAS[0][cmd]
                    +"不支持此用户数据, (userdata="+userdata+")");
        }
    }

    public static final String getProtocolCmd(int cmd) {
        checkProtocolCmd(cmd);
        return PROTOCOL_CMDS_USERDATAS[0][cmd];
    }

    public static final String[] getProtocolCmds() {
        return PROTOCOL_CMDS_USERDATAS[0];
    }

    public static final String getCmdUserdata(int cmd, int userdata) {
        checkProtocolCmd(cmd);
        checkCmdUserdata(cmd, userdata);
        return PROTOCOL_CMDS_USERDATAS[cmd+1][userdata];
    }

    public static final String[] getCmdUserdatas(int cmd) {
        checkProtocolCmd(cmd);
        return PROTOCOL_CMDS_USERDATAS[cmd+1];
    }

}
