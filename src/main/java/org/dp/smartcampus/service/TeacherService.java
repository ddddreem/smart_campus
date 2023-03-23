package org.dp.smartcampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dp.smartcampus.pojo.Clazz;
import org.dp.smartcampus.pojo.LoginForm;
import org.dp.smartcampus.pojo.Teacher;

import java.util.List;

/**
 * @Description: Teacher服务接口
 * @Author: dreem pong
 * @Date: 2023/3/18 11:21
 */
public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(Integer userId);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher);

    List<Teacher> getTeachers();
}
