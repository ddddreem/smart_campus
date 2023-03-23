package org.dp.smartcampus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Clazz;
import org.dp.smartcampus.pojo.Teacher;
import org.dp.smartcampus.service.ClazzService;
import org.dp.smartcampus.service.TeacherService;
import org.dp.smartcampus.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取所有的教师信息")
    @GetMapping("/getTeachers")
    public Result getTeachers(){
        List<Teacher> teachers = teacherService.getTeachers();
        return Result.ok(teachers);
    }

    @ApiOperation("获取所有教师项")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Teacher teacher
    ){
        Page<Teacher> pageParam = new Page<>(pageNo, pageSize);
        IPage<Teacher> page = teacherService.getTeachersByOpr(pageParam, teacher);
        return Result.ok(page);
    }

    @ApiOperation("添加或者更新教师信息")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@ApiParam("新增或者修改的教师表单信息") @RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    @ApiOperation("删除单条或者多条教师记录")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids){
        boolean b = teacherService.removeByIds(ids);
        return b ? Result.ok() : Result.fail().message("系统错误...");
    }
}