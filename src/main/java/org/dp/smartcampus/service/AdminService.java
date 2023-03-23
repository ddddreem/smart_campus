package org.dp.smartcampus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dp.smartcampus.pojo.Admin;
import org.dp.smartcampus.pojo.LoginForm;

/**
 * @Description: Admin服务接口
 * @Author: dreem pong
 * @Date: 2023/3/18 11:21
 */
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(Integer userId);
}
