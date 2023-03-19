package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.StudentMapper;
import org.dp.smartcampus.pojo.Student;
import org.dp.smartcampus.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: student服务接口实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:28
 */
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
