package org.dp.smartcampus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Grade {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String introduction;
    private String manager;
    private String email;
    private String telephone;
}
