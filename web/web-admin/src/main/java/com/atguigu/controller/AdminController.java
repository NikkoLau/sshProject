package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.AdminRole;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private String Success = "common/successPage";

    @Reference
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Reference
    private RoleService roleService;

    @RequestMapping
    public String index(Map map, HttpServletRequest request) {

        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);
        map.put("filters", filters);
        map.put("page", page);
        return "admin/index";
    }

    @RequestMapping("create")
    public String toCreate() {
        return "admin/create";
    }

    @RequestMapping("save")
    public String saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.insert(admin);
        return Success;
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable Long id, Map map) {
        Admin admin = adminService.getById(id);
        map.put("admin", admin);
        return "admin/edit";
    }

    @RequestMapping("update")
    public String update(Admin admin) {
        adminService.update(admin);
        return Success;
    }

    @RequestMapping("/assignShow/{id}")
    public String assignShow(@PathVariable("id") Long id, ModelMap modelMap) {
        Admin admin = adminService.getById(id);
        Map<String, Object> map = roleService.assginRoleById(id);
        modelMap.addAttribute("adminId", admin.getId());
        modelMap.addAllAttributes(map);
        return "admin/assignShow";
    }

    @RequestMapping("/assignRole")
    public String assignRole(@RequestParam("adminId") Long adminId, @RequestParam("roleIds") Long[] roleIds) {
//        添加角色
        roleService.addRole(adminId,roleIds);
        return "common/successPage";
    }
}
