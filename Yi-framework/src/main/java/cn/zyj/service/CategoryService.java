package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-09-21 15:08:28
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

