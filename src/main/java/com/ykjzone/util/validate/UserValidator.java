package com.ykjzone.util.validate;

import com.ykjzone.pojo.User;
import com.ykjzone.service.UserService;
import com.ykjzone.service.impl.UserServiceImpl;
import com.ykjzone.util.ImageCaptcha;
import com.ykjzone.util.RESTful;
import com.ykjzone.util.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Pattern;

public class UserValidator {
    @Autowired
    private static UserService userService;

    private HttpServletRequest request;
    private HttpSession session;
    private String message;

    private String telephone;
    private String username;
    private String password;
    private String password2;
    private String img_captcha;
    private String sms_captcha;

    public UserValidator(HttpServletRequest request){
        this.request = request;
        this.session = request.getSession();

        this.telephone = (String) request.getParameter("telephone");
        System.out.println(this.telephone);
        this.username = (String) request.getParameter("username");
        this.password = (String) request.getParameter("password");
        this.password2 = (String) request.getParameter("password2");
        this.img_captcha = (String) request.getParameter("img_captcha");
        this.sms_captcha = (String) request.getParameter("sms_captcha");
    }

    public boolean clean(){
        if(!cleanTelephone()) return false;
        if(!cleanUsername()) return false;
        if(!cleanPassword(password)) return false;
        if(!cleanPassword(password2)) return false;
        if(!password.equals(password2)) {
            message = "两次输入的密码不一致";
            return false;
        }
        if(!cleanImgCaptcha()) return false;
        if(!cleanSMSCaptcha()) return false;
        return true;
    }

    public boolean cleanTelephone(){
        if(telephone == null || telephone.equals("")){
            message = "手机号不能为空";
            return false;
        }

        String pa_telephone = "1[3-9]\\d{9}";
        boolean is_match = Pattern.matches(pa_telephone, telephone);
        if(!is_match) {
            message = "手机号输入有误";
            return false;
        }

        User user = userService.getByTelephone(telephone);
        String user_telephone = user.getTelephone();
        if(user_telephone != null && user_telephone.equals(telephone)) {
            message = "此手机号已注册";
            return false;
        }

        return true;
    }

    public boolean cleanUsername(){
        if(username == null || username.equals("")) {
            message = "用户名不能为空";
            return false;
        }
        if(username.length() < 2) {
            message = "用户名不能小于2位";
            return false;
        }
        if(username.length() > 12) {
            message = "用户名不能大于12位";
            return false;
        }

        User user = userService.getByUsername(username);
        String user_username = user.getUsername();
        if(user_username != null && user_username.equals(username)) {
            message = "此用户名已经被占用";
            return false;
        }

        return true;
    }

    public boolean cleanImgCaptcha(){
        if(img_captcha == null) {
            message = "验证码不能为空";
            return false;
        }

        String session_image_code = (String) this.session.getAttribute(ImageCaptcha.RANDOMCODEKEY);
        img_captcha = img_captcha.toLowerCase();
        session_image_code = session_image_code.toLowerCase();
        if(img_captcha.equals(session_image_code) == false) {
            message = "图形验证码错误";
            return false;
        }

        return true;
    }

    public boolean cleanSMSCaptcha(){
        Jedis jedis = Redis.JEDIS;
        String redis_sms_captcha = jedis.get(telephone);
        if(redis_sms_captcha == null || redis_sms_captcha.equals(sms_captcha) == false){
            message = "短信验证码输入有误";
            return false;
        }
        return true;
    }

    public boolean cleanPassword(String password) {
        if(password == null || password.equals("")) {
            message = "密码不能为空";
            return false;
        }
        if(password.length() < 6) {
            message = "密码长度不能小于6位";
            return false;
        }
        if(password.length() > 16) {
            message = "密码长度不能大于16位";
            return false;
        }
        return true;
    }

    public String getMessage(){
        return message;
    }

    public void save() {
        User user = new User();
        user.setTelephone(telephone);
        user.setUsername(username);
        user.setPassword(password);
        user.setDate_joined(new Date());
        user.setIs_active(true);
        user.setIs_author(false);
        user.setIs_staff(false);
        user.setIs_superuser(false);
        userService.register(user);
    }
}
