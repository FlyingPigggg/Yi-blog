package cn.zyj.controller;

import cn.zyj.domain.ResponseResult;
import cn.zyj.domain.entity.Link;
import cn.zyj.domain.vo.PageVo;
import cn.zyj.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 获取友链列表
     */
    @GetMapping("/list")
    public ResponseResult list(Link link, Integer pageNum, Integer pageSize)
    {
        PageVo pageVo = linkService.selectLinkPage(link, pageNum, pageSize);
        return ResponseResult.okResult(pageVo);
    }

    @PostMapping
    public ResponseResult add(@RequestBody Link link){
        linkService.save(link);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult edit(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }



    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }
}
