package org.dp.smartcampus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Grade;
import org.dp.smartcampus.service.GradeService;
import org.dp.smartcampus.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@Api(tags="年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ApiOperation("获取所有年级信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }

    @ApiOperation("获取所有年级项")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @ApiParam("页号") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页大小") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("模糊查询年级名称") @RequestParam(value = "gradeName", required = false) String gradeName
    ){
        Page<Grade> pageParam = new Page<>(pageNo, pageSize); // 封装分页信息

        IPage<Grade> page = gradeService.getGradesByOps(pageParam, gradeName); // 获取分页结果
        return Result.ok(page);
    }

    @ApiOperation("添加或者更新年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("新增或者修改的表单信息") @RequestBody Grade grade){
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @ApiOperation("删除单条或者多条年级记录")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("需要删除的id集合") @RequestBody List<Integer> ids){
        boolean b = gradeService.removeByIds(ids);
        return b ? Result.ok() : Result.fail().message("系统错误...");
    }
}