package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.StudentMapper;
import org.dp.smartcampus.pojo.Admin;
import org.dp.smartcampus.pojo.LoginForm;
import org.dp.smartcampus.pojo.Student;
import org.dp.smartcampus.service.StudentService;
import org.dp.smartcampus.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Description: student服务接口实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:28
 */
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public Student getStudentById(Integer userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public IPage<Student> getStudentsByOpr(Page<Student> pageParam, Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        String name = student.getName();
        String clazzName = student.getClazzName();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(clazzName)){
            queryWrapper.eq("clazz_name", clazzName);
        }
        queryWrapper.orderByDesc("id");
        Page<Student> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }
}
