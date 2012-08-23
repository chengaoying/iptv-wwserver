package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.global.Configurations;
import org.springframework.stereotype.Service;

@Service
public class ServerHallProvider {
    private Configurations conf;
    public void setConfigurations(Configurations conf) {
        this.conf = conf;
    }
}
