package org.dp.smartcampus.pojo;

import lombok.Data;

@Data
public class LoginForm { // 用户登录表单信息接收类

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
