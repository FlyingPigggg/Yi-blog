package cn.zyj.service.impl;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.dto.TagListDto;
import cn.zyj.domain.entity.Tag;
import cn.zyj.domain.entity.User;
import cn.zyj.domain.vo.PageVo;
import cn.zyj.domain.vo.TagVo;
import cn.zyj.enums.AppHttpCodeEnum;
import cn.zyj.exception.SystemException;
import cn.zyj.mapper.TagMapper;
import cn.zyj.service.TagService;
import cn.zyj.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-10-06 14:59:24
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();

        // StringUtils.hasText(tagListDto.getName())为true表示不为空
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        //对数据进行非空判断
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.NAME_NOT_NULL);
        }
        if(!StringUtils.hasText(tag.getRemark())){
            throw new SystemException(AppHttpCodeEnum.REMARK_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(nameExist(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(remarkExist(tag.getRemark())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        //存入数据库
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        removeById(id);
        return ResponseResult.okResult("删除成功！");
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        return ResponseResult.okResult(getById(id));
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }


    private boolean nameExist(String name) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getName, name);
        return  count(queryWrapper) > 0 ;
    }

    private boolean remarkExist(String remark) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getRemark, remark);
        return  count(queryWrapper) > 0 ;
    }
}
