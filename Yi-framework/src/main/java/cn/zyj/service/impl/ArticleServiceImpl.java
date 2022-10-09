package cn.zyj.service.impl;

import cn.zyj.constants.SystemConstants;
import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.AddArticleDto;
import cn.zyj.domain.dto.ArticleListDto;
import cn.zyj.domain.entity.Article;
import cn.zyj.domain.entity.ArticleTag;
import cn.zyj.domain.entity.Category;
import cn.zyj.domain.vo.*;
import cn.zyj.enums.AppHttpCodeEnum;
import cn.zyj.mapper.ArticleMapper;
import cn.zyj.mapper.ArticleTagMapper;
import cn.zyj.service.ArticleService;
import cn.zyj.service.ArticleTagService;
import cn.zyj.service.CategoryService;
import cn.zyj.utils.BeanCopyUtils;
import cn.zyj.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public ResponseResult hotArticleList() {

        // 查询热门文章 封装ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        // 得是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        // 得降序排列
        queryWrapper.orderByDesc(Article::getViewCount);

        // 只取10个文章
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        List<Article> records = page.getRecords();


        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        //查询categoryName
        List<Article> articles = page.getRecords();

        articles.stream()
                .map(article ->
                        article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());


        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        //封装page
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }


    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }



    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);


        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult pageArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto) {

        //分页查询
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        // StringUtils.hasText(tagListDto.getName())为true表示不为空
        queryWrapper.like(StringUtils.hasText(articleListDto.getTitle()), Article::getTitle, articleListDto.getTitle());
        queryWrapper.like(StringUtils.hasText(articleListDto.getSummary()), Article::getSummary, articleListDto.getSummary());


        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult selectArticleById(Long id) {
        Article article = getById(id);
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);

        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleVo.getId());

        List<ArticleTag> articleTagList = articleTagService.list(queryWrapper);
        List<String> tags = new ArrayList<>();
        for (ArticleTag articleTag : articleTagList){
            tags.add(articleTag.getTagId().toString());
        }

        articleVo.setTags(tags);

        return ResponseResult.okResult(articleVo);
    }

    @Override
    public ResponseResult updateArticle(ArticleVo articleVo) {

        Article article = BeanCopyUtils.copyBean(articleVo, Article.class);
        updateById(article);

        // 删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);

        //添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleVo.getTags().stream()
                .map(tagId -> new ArticleTag(articleVo.getId(), Long.valueOf(tagId)))
                .collect(Collectors.toList());

        articleTagService.saveBatch(articleTags);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long id) {

        removeById(id);

//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Article::getId, id);
//        Article article = getBaseMapper().selectOne(queryWrapper);
//        article.setDelFlag(SystemConstants.ARTICLE_STATUS_DRAFT);
//        updateById(article);
        return ResponseResult.okResult();
    }

}
