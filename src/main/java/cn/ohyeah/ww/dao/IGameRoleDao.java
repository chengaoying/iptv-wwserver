package cn.ohyeah.ww.dao;

import cn.ohyeah.ww.model.GameRole;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IGameRoleDao {
    public void create(GameRole role);
    public int update(GameRole role);
    @Cacheable("gameRole")
    public GameRole read(int roleId);
    public GameRole readByName(String roleName);
    public List<GameRole> readRoles(String userId);
}
