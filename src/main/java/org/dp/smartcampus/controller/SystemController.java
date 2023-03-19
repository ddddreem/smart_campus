package org.dp.smartcampus.controller;

import com.sun.deploy.net.HttpResponse;
import org.dp.smartcampus.util.CreateVerifiCodeImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Description:
 * @Author: dreem pong
 * @Date: 2023/3/18 11:42
 */
@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @GetMapping("getVerifiCodeImage")
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