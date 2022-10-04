package cn.zyj.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {

    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;


}
