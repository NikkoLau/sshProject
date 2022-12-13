package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Reference
    private AdminService adminService;
    @Reference
    private PermissionService permissionService;
    @GetMapping("/")
    public String index(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Admin admin = adminService.getByUsername(user.getUsername());
       List<Permission> permissionList =  permissionService.findMenuPermissionByAdminId(admin.getId());
        modelMap.addAttribute("admin", admin);
        modelMap.addAttribute("permissionList",permissionList);
        return "frame/index";
    }
    @GetMapping("/main")
    public String main(){
        return "frame/main";
    }

    @RequestMapping("/login")
    public String login(){
        return "frame/login.html";
    }
}
