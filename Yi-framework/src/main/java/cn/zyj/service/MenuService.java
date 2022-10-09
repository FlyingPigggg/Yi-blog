package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.MenuListDto;
import cn.zyj.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 02:16:54
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult menuList(MenuListDto menuListDto);

    boolean hasChild(Long menuId);
}

