package com.itheima.controller;

import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : 光辉的mac
 * @ClassName UserController
 * @date : 2020/9/8 16:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

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
}
