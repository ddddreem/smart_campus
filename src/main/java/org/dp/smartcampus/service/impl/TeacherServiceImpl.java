package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.TeacherMapper;
import org.dp.smartcampus.pojo.Teacher;
import org.dp.smartcampus.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: teacher服务接口实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:31
 */
@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
