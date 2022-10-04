package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-09-25 09:16:49
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

