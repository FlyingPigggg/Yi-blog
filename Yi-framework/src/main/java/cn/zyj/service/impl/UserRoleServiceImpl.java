package cn.zyj.service.impl;

import cn.zyj.domain.entity.UserRole;
import cn.zyj.mapper.UserRoleMapper;
import cn.zyj.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-09 22:42:32
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
