package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Role;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RolePermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/role")
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleController extends BaseController{

    private String Success = "common/successPage";

    @Reference
    private RoleService roleService;

    @Reference
    private PermissionService permissionService;
    @Reference
    private RolePermissionService rolePermissionService;

    @RequestMapping
    public String index(Map map,HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);
        map.put("filters",filters);
        map.put("page",page);
        return "role/index";

    }
    @GetMapping("/create")
    public String create(){
        return "role/create";
    }

    /**
     * 添加信息
     * @param role
     * @return
     */
    @PostMapping("/save")
    public String save(Role role){
        roleService.insert(role);
        return Success;
    }

    /**
     * 去更新页面
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public String toEdit(Map map,@PathVariable Long id){
        Role role = roleService.getById(id);
        if (role==null){
            return "";
        }
        map.put("role",role);
        System.out.println(role);
        return "role/edit";
    }

    /**
     * 更新信息
     * @param role
     * @return
     */
    @PostMapping("/update")
    public String update(Role role){

        roleService.update(role);
        return Success;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable  Long id){
       roleService.delete(id);
       return Success;
    }

    @RequestMapping("/assignShow/{id}")
    public String assignShow(@PathVariable("id")Long roleId, ModelMap modelMap){
        List<Map<String, Object>> zNodes = permissionService.findAll(roleId);
        modelMap.addAttribute("roleId", roleId);
        modelMap.addAttribute("zNodes", zNodes);
        return "role/assginShow";

    }
}
