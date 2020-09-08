package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.SysUser;
import com.itheima.service.UserService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName UserController
 * @date : 2020/9/8 16:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/loginSuccess")
    public void loginSuccess(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:83/pages/main.html");
    }

    @RequestMapping("/loginFail")
    public void loginFail(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:83/pages/login.html?message=loginFail");
    }

    @RequestMapping("/getUsername")
    public Result getUsername(HttpSession session){
        session.getAttribute("SPRING_SECURITY_CONTEXT");
        session.getAttributeNames(); //可以获取session中所有的参数名
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof User){
            User user = (User) principal;
            username = user.getUsername();
        }
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
    }

    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,String> map,HttpSession session){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //第一输入的密码
        String pass = map.get("pass");
        //第二次输入的密码
        String checkPass = map.get("checkPass");
        if (pass != null && checkPass != null && pass.equals(checkPass)){
            session.getAttribute("SPRING_SECURITY_CONTEXT");
            session.getAttributeNames(); //可以获取session中所有的参数名
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username = null;
            if (principal instanceof User){
                User user = (User) principal;
                username = user.getUsername();
            }
            //根据用户名获取密码
            SysUser sysUser = userService.findUserByUsername(username);
            boolean b = passwordEncoder.matches( pass,sysUser.getPassword());
            if (!b){
                return new Result(false,"旧密码不正确");
            }
            //新的密码
            String newpwd = map.get("newpwd");
            //加密密码
            String encodePWD = passwordEncoder.encode(newpwd);
            userService.editPasswordByUsername(username,encodePWD);
            return new Result(true,"修改密码成功");
        }
        return new Result(false,"请输入密码在修改");
    }


}
