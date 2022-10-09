package cn.zyj.service;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.TagListDto;
import cn.zyj.domain.entity.Tag;
import cn.zyj.domain.vo.PageVo;
import cn.zyj.domain.vo.TagVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-10-06 14:59:22
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult deleteTag(Long id);

    ResponseResult updateTag(Tag tag);

    ResponseResult getTag(Long id);

    List<TagVo> listAllTag();
}

