package cn.zyj.service;

import cn.zyj.domain.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2022-10-09 22:11:33
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}

