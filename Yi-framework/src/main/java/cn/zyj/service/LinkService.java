package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-09-25 00:20:09
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

