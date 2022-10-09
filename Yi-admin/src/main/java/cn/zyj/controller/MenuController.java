package cn.zyj.controller;


import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.ArticleListDto;
import cn.zyj.domain.dto.MenuListDto;
import cn.zyj.domain.entity.Menu;
import cn.zyj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult menuList(MenuListDto menuListDto){
        return menuService.menuList(menuListDto);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping(value = "/{menuId}")
    public ResponseResult getInfo(@PathVariable Long menuId)
    {
        return ResponseResult.okResult(menuService.getById(menuId));
    }

    @PutMapping
    public ResponseResult edit(@RequestBody Menu menu) {
        if (menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500,"修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{menuId}")
    public ResponseResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChild(menuId)) {
            return ResponseResult.errorResult(500,"存在子菜单不允许删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }


}
