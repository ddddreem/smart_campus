package org.dp.smartcampus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Student;
import org.dp.smartcampus.pojo.Teacher;
import org.dp.smartcampus.service.StudentService;
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
@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;
//
//    @ApiOperation("获取所有的教师信息")
//    @GetMapping("/getT")
//    public Result getTeachers(){
//        List<Teacher> teachers = teacherService.getTeachers();
//        return Result.ok(teachers);
//    }

    @ApiOperation("获取所有学生项")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Student student
    ){
        Page<Student> pageParam = new Page<>(pageNo, pageSize);
        IPage<Student> page = studentService.getStudentsByOpr(pageParam, student);
        return Result.ok(page);
    }

    @ApiOperation("添加或者更新教师信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@ApiParam("新增或者修改的学生表单信息") @RequestBody Student student){
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation("删除单条或者多条教师记录")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids){
        boolean b = studentService.removeByIds(ids);
        return b ? Result.ok() : Result.fail().message("系统错误...");
    }
}