package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerHallInfo;
import org.springframework.stereotype.Service;

@Service
public class ServerHallProvider {
    private Configurations conf;
    public void setConfigurations(Configurations conf) {
        this.conf = conf;
    }
}
