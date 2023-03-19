package org.dp.smartcampus.pojo;

import lombok.Data;

@Data
public class LoginForm { // 用户登录表单信息

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
