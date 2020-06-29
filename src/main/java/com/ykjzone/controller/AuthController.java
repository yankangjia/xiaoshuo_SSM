package com.ykjzone.controller;

import com.ykjzone.pojo.User;
import com.ykjzone.service.UserService;
import com.ykjzone.util.ImageCaptcha;
import com.ykjzone.util.RESTful;
import com.ykjzone.util.Redis;
import com.ykjzone.util.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("/xsauth")
public class AuthController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response){
        String telephone = (String) request.getParameter("telephone");
        System.out.println(telephone);
        UserValidator userValidator = new UserValidator(request);
        try {
            if(userValidator.clean()){
                userValidator.save();
                response.getWriter().write(RESTful.ok());
            } else{
                String message = userValidator.getMessage();
                    response.getWriter().write(RESTful.params_error(message,null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/img_captcha")
    public void imgCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            ImageCaptcha randomValidateCode = new ImageCaptcha();
            randomValidateCode.getRandcode(request, response);//输出验证码图片
        } catch(Exception e){
            e.printStackTrace();
        }
        //从session中取出随机验证码
        System.out.println(request.getSession().getAttribute("RANDOMREDISKEY"));
    }

    @RequestMapping(value = "/sms_captcha", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String SMSCaptcha(String telephone){
        Jedis jedis = Redis.JEDIS;
        // 生成验证码
        String captcha = "";
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            int number = random.nextInt(10);
            captcha += String.valueOf(number);
        }
        // 保存到缓存redis
        jedis.set(telephone, captcha);
        jedis.expire(telephone, 5 * 60);
        // 给手机发送验证码，此步骤省略
        // 返回
        Map<String,String> data = new HashMap<>();
        data.put("code", captcha);
        return RESTful.result(200,"",data);
    }

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
