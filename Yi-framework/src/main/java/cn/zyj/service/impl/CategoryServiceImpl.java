package cn.zyj.service.impl;

import cn.zyj.constants.SystemConstants;
import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.Article;
import cn.zyj.domain.entity.Category;
import cn.zyj.domain.vo.CategoryVo;
import cn.zyj.mapper.CategoryMapper;
import cn.zyj.service.ArticleService;
import cn.zyj.service.CategoryService;
import cn.zyj.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-09-21 15:08:29
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {

        // 查询文章已发布的
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);

        // 获取文章中去重的id
        Set<Long> articleIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查询文章对应的分类
        List<Category> categories = listByIds(articleIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        // 封装Vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
