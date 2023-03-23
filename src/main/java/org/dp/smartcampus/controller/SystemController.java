package org.dp.smartcampus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dp.smartcampus.pojo.Admin;
import org.dp.smartcampus.pojo.LoginForm;
import org.dp.smartcampus.pojo.Student;
import org.dp.smartcampus.pojo.Teacher;
import org.dp.smartcampus.service.AdminService;
import org.dp.smartcampus.service.StudentService;
import org.dp.smartcampus.service.TeacherService;
import org.dp.smartcampus.util.CreateVerifiCodeImage;
import org.dp.smartcampus.util.JwtHelper;
import org.dp.smartcampus.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@Api(tags = "系统控制器")
@RestController
@RequestMapping("/sms/system")
@SuppressWarnings("uncheck")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("文件上传同意接口")
    @PostMapping("/headerImgUpload")
    public Result headerImgUpload(@ApiParam("头像文件")@RequestPart("multipartFile") MultipartFile multipartFile){
        //使用UUID随机生成文件名
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        //生成新的文件名字
        String filename = uuid.concat(multipartFile.getOriginalFilename());
        //生成文件的保存路径(实际生产环境这里会使用真正的文件存储服务器)
        String portraitPath ="d:/projects/java/SpringBoot/smartcampus/target/classes/public/upload/".concat(filename);
        new Runnable() {
            @Override
            public void run() {
                //保存文件
                try {
                    Logger logger = Logger.getLogger("org.dp.smartcampus.controller.TeacherController");
                    logger.info("file-upload-start...");
                    multipartFile.transferTo(new File(portraitPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.run();
//        //保存文件
//        try {
//            multipartFile.transferTo(new File(portraitPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String headerImg ="upload/"+filename;
        return Result.ok(headerImg);
    }

    @ApiOperation("获取登录的用户信息")
    @GetMapping("/getInfo")
    public Result getInfo(@ApiParam("之前登录的时候保存的token信息") @RequestHeader("token") String token){
        if(JwtHelper.isExpiration(token)){
            return Result.fail().message("token过期...");
        }
        Integer userId = JwtHelper.getUserId(token).intValue();
        Integer userType = JwtHelper.getUserType(token);
        Map<String, Object> map = new HashMap<>();
        map.put("userType", userType);
        try{
        switch(userType){
            case 1:
                Admin admin = adminService.getAdminById(userId);
                if(null != admin){
                    map.put("user", admin);
                }else{
                    throw new RuntimeException("管理员账户错误...");
                }
                break;
            case 2:
                Student student = studentService.getStudentById(userId);
                if(null != student){
                    map.put("user", student);
                }else{
                    throw new RuntimeException("学生账户错误...");
                }
                break;
            case 3:
                Teacher teacher = teacherService.getTeacherById(userId);
                if(null != teacher){
                    map.put("user", teacher);
                }else{
                    throw new RuntimeException("老师账户错误...");
                }
                break;
        }}catch(RuntimeException re){
            re.printStackTrace();
            return Result.fail().message(re.getMessage());
        }
        return Result.ok(map);
    }

    @ApiOperation("前台登录方法")
    @PostMapping("/login")
    public Result login(@ApiParam("前台登录页面填写的用户信息") @RequestBody LoginForm loginForm, HttpServletRequest request) {
        // 验证码校验
        HttpSession session = request.getSession();
        String sessionVerifiCode = (String) session.getAttribute("verifiCode");
        String loginVerifiCode = loginForm.getVerifiCode();
        if("".equals(sessionVerifiCode) || null == sessionVerifiCode){
            return Result.fail().message("验证码过期...");
        }
        if(!loginVerifiCode.equalsIgnoreCase(sessionVerifiCode)){
            return Result.fail().message("验证码错误...");
        }
        // 从 session 域中删除验证码
        session.removeAttribute("verifiCode");

        Map<String, Object> map = new HashMap<>();

        try{
        switch(loginForm.getUserType()){
            case 1:
                Admin admin = adminService.login(loginForm); // 校验获取用户信息
                if(null != admin){
                    String token = JwtHelper.createToken(admin.getId().longValue(), 1);
                    map.put("token", token);
                }else {
                    throw new RuntimeException("用户名或密码错误...");
                }
                break;
            case 2:
                Student student = studentService.login(loginForm); // 校验获取用户信息
                if(null != student){
                    String token = JwtHelper.createToken(student.getId().longValue(), 2);
                    map.put("token", token);
                }else {
                    throw new RuntimeException("用户名或密码错误...");
                }
                break;
            case 3:
                Teacher teacher = teacherService.login(loginForm); // 校验获取用户信息
                if(null != teacher){
                    String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
                    map.put("token", token);
                }else {
                    throw new RuntimeException("用户名或密码错误...");
                }
                break;
        }}catch(RuntimeException re){
            re.printStackTrace();
            return Result.fail().message(re.getMessage());
        }
        // 分类型进行校验
        return Result.ok(map);
    }

    @ApiOperation("前台登录页面获取验证码")
    @GetMapping("getVerifiCodeImage") // 获取验证码方法
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){

        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();

        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());

        HttpSession session = request.getSession();

        session.setAttribute("verifiCode", verifiCode);

        ServletOutputStream os = null;

        try{
            os = response.getOutputStream();
            ImageIO.write(verifiCodeImage, "JPEG", os);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}