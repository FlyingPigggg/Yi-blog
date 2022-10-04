package cn.zyj.mapper;

import cn.zyj.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-25 09:16:46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
