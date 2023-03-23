package org.dp.smartcampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dp.smartcampus.pojo.Grade;

import java.util.List;

/**
 * @Description: Grade服务接口
 * @Author: dreem pong
 * @Date: 2023/3/18 11:21
 */
public interface GradeService extends IService<Grade> {
    IPage<Grade> getGradesByOps(Page<Grade> pageParam, String gradeName);

    List<Grade> getGrades();
}
