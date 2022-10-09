package cn.zyj.controller;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.AddArticleDto;
import cn.zyj.domain.dto.ArticleListDto;
import cn.zyj.domain.vo.ArticleVo;
import cn.zyj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult articleList(Integer pageNum, Integer pageSize,
                                      ArticleListDto articleListDto){
        return articleService.pageArticleList(pageNum, pageSize, articleListDto);
    }

    @GetMapping("/{id}")
    public ResponseResult selectArticleById(@PathVariable("id") Long id){
        return articleService.selectArticleById(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleVo articleVo){
        return articleService.updateArticle(articleVo);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }


}
