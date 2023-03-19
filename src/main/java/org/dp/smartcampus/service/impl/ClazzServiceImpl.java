package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.ClazzMapper;
import org.dp.smartcampus.pojo.Clazz;
import org.dp.smartcampus.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: Clazz接口服务实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:26
 */
@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
}
