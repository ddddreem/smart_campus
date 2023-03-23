package org.dp.smartcampus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Clazz;
import org.dp.smartcampus.service.ClazzService;
import org.dp.smartcampus.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @ApiOperation("获取所有的班级信息")
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzes = clazzService.getClazzs();
        return Result.ok(clazzes);
    }

    @ApiOperation("获取所有班级项")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzs(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Clazz clazz
    ){
        Page<Clazz> pageParam = new Page<>(pageNo, pageSize);
        IPage<Clazz> page = clazzService.getClazzsByOpr(pageParam, clazz);
        return Result.ok(page);
    }

    @ApiOperation("添加或者更新班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@ApiParam("新增或者修改的班级表单信息") @RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    @ApiOperation("删除单条或者多条班级记录")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids){
        boolean b = clazzService.removeByIds(ids);
        return b ? Result.ok() : Result.fail().message("系统错误...");
    }
}