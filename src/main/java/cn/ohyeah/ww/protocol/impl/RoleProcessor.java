package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.model.GameRole;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleProcessor extends AbstractProcessor {
    private RoleService roleServ;

    @Autowired
    public void setRoleService(RoleService roleServ) {
        this.roleServ = roleServ;
    }

    public List<GameRole> queryRoles(ProcessContext context, ByteBuffer req) {
        String userId = req.readUTF();
        return roleServ.queryRoles(userId);
    }

    public GameRole create(ProcessContext context, ByteBuffer req) {
        String roleName = req.readUTF();
        String userId = req.readUTF();
        String password = req.readUTF();
        return roleServ.create(roleName, userId, password);
    }
}
