package org.dp.smartcampus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_teacher")
public class Teacher {

    @TableId(value = "id", type = IdType.AUTO)
    private String tno;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String portraitPath;
    private String clazzName;
}
