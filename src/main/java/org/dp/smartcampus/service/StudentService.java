package org.dp.smartcampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dp.smartcampus.pojo.LoginForm;
import org.dp.smartcampus.pojo.Student;

/**
 * @Description: Student服务接口
 * @Author: dreem pong
 * @Date: 2023/3/18 11:21
 */
public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getStudentById(Integer userId);

    IPage<Student> getStudentsByOpr(Page<Student> pageParam, Student student);
}
