package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.GradeMapper;
import org.dp.smartcampus.pojo.Grade;
import org.dp.smartcampus.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: grade服务接口实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:27
 */
@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
}
