package com.itheima.security.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : 光辉的mac
 * @ClassName UserDetailsServiceImpl
 * @date : 2020/9/8 16:17
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取密码
        SysUser sysUser = userService.findUserByUsername(username);
        if (sysUser != null){
            Collection<GrantedAuthority> authoritieList = new ArrayList<>();
            //把所有的权限加入到集合中
            for (Role role : sysUser.getRoles()) {
                authoritieList.add(new SimpleGrantedAuthority(role.getKeyword()));
                for (Permission permission : role.getPermissions()) {
                    authoritieList.add(new SimpleGrantedAuthority(permission.getKeyword()));
                }
            }

            UserDetails user = new User(username, sysUser.getPassword(), authoritieList);
            return user;
        }
       return null;
    }

}
