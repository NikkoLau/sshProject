package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private PermissionService permissionService;

    @RequestMapping
    public String index(ModelMap modelMap){
        List<Permission> permissions =  permissionService.findAllMenu();
        modelMap.addAttribute("list",permissions);
        return "permission/index";
    }
    @RequestMapping("/create")
    public String create(ModelMap modelMap,Permission permission){
        modelMap.addAttribute("permission",permission);
        return "permission/create";
    }
    @RequestMapping("/save")
    public String save(Permission permission){
        if (permission!=null){
            permissionService.insert(permission);
        }
        return "common/successPage";
    }

    @RequestMapping("/edit/{id}")
    public String toUpdate(@PathVariable("id")Long id, ModelMap modelMap){
        Permission permission = permissionService.getById(id);
        modelMap.addAttribute("permission",permission);
        return "permission/edit";
    }

    @RequestMapping("/update")
    public String update(Permission permission){
        if (permission!=null){
            permissionService.update(permission);
        }
        return "common/successPage";
    }

    @RequestMapping("/delete/{id}")
    public  String delete(@PathVariable("id")Long id){
        permissionService.delete(id);
        return "common/successPage";
    }
}
