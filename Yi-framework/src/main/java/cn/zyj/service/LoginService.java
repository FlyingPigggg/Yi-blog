package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
