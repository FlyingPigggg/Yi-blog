package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.AddArticleDto;
import cn.zyj.domain.dto.ArticleListDto;
import cn.zyj.domain.entity.Article;
import cn.zyj.domain.vo.ArticleVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);

    ResponseResult pageArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto);

    ResponseResult selectArticleById(Long id);

    ResponseResult updateArticle(ArticleVo articleVo);

    ResponseResult deleteArticle(Long id);
}
