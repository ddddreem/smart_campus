package org.dp.smartcampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dp.smartcampus.mapper.ClazzMapper;
import org.dp.smartcampus.pojo.Clazz;
import org.dp.smartcampus.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Description: Clazz接口服务实现
 * @Author: dreem pong
 * @Date: 2023/3/18 11:26
 */
@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> pageParam, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        String name = clazz.getName();
        String gradeName = clazz.getGradeName();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(gradeName)){
            queryWrapper.eq("grade_name", gradeName);
        }
        queryWrapper.orderByDesc("id");
        Page<Clazz> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}
