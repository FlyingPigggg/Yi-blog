package cn.zyj.mapper;

import cn.zyj.domain.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;


/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-08 23:52:08
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    void deleteById(Long id);

    void insertArticleTag(Long articleId, Long tagId);

}
