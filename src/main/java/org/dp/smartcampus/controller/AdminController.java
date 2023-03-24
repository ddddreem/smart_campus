package org.dp.smartcampus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Admin;
import org.dp.smartcampus.service.AdminService;
import org.dp.smartcampus.util.MD5;
import org.dp.smartcampus.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@Api(tags = "管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("获取所有用户信息")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("页号") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("页容量") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("管理员名称模糊匹配条件") @RequestParam(value = "adminName", required = false) String adminName
    ){
        Page<Admin> pageParam = new Page<>(pageNo, pageSize);
        IPage<Admin> page = adminService.getAllAdmin(pageParam, adminName);
        return Result.ok(page);
    }

    @ApiOperation("添加或者更新管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@ApiParam("新增或者修改的管理员表单信息") @RequestBody Admin admin){
        Integer id = admin.getId();
        String password = admin.getPassword();
        if(null == id){
            admin.setPassword(MD5.encrypt(password));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @ApiOperation("删除单条或者多条管理员记录")
    @DeleteMapping("/deleteAdmin")
    public Result deleteTeacher(@RequestBody List<Integer> ids){
        boolean b = adminService.removeByIds(ids);
        return b ? Result.ok() : Result.fail().message("系统错误...");
    }
}
