package cn.zyj.domain.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    @TableId
    private Long id;

    //分类名
    private String name;

    //描述
    private String description;

}
