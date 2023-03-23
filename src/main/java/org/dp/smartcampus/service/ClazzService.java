package org.dp.smartcampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dp.smartcampus.pojo.Clazz;

import java.util.List;

/**
 * @Description: Clazz服务接口
 * @Author: dreem pong
 * @Date: 2023/3/18 11:21
 */
public interface ClazzService extends IService<Clazz> {
    IPage<Clazz> getClazzsByOpr(Page<Clazz> pageParam, Clazz clazz);

    List<Clazz> getClazzs();
}
