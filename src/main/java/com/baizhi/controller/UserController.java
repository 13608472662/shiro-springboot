package com.baizhi.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("login")
    public String login(String username, String password, String rememberme) {
        //通过工具类获取主体
        Subject subject = SecurityUtils.getSubject();
        //创建Token（用户名，密码）
        UsernamePasswordToken authenticationToken = new UsernamePasswordToken(username, password);
        //判断是否选中记住我七天
        if (rememberme != null && rememberme.equals("1")) {
            authenticationToken.setRememberMe(true);//开启记住我模式
        }

        String message = null;
        try {
            //将token作为参数-》登录
            subject.login(authenticationToken);
            return "redirect:/main/main.jsp";
        } catch (IncorrectCredentialsException e) {
            message = "mm不正确";
            System.out.println("认证结果: " + message);
            return "redirect:/user/login.jsp";
        } catch (UnknownAccountException e) {
            message = "用户名不正确";
            System.out.println("认证结果: " + message);
            return "redirect:/user/login.jsp";
        }


    }
    /*@RequestMapping("logout")
    public String logout(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "redirect:/user/login.jsp";
    }*/

}
