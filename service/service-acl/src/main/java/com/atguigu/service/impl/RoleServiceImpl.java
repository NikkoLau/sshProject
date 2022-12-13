package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Admin;
import com.atguigu.entity.AdminRole;
import com.atguigu.entity.Role;
import com.atguigu.mapper.AdminMapper;
import com.atguigu.mapper.AdminRoleMapper;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.RoleMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public List<Role> findAll() {

        return roleMapper.findAll();
    }


    /**
     * 分配角色
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> assginRoleById(Long id) {
        ArrayList<Role> noAssginRoleList = new ArrayList<>();
        ArrayList<Role> assginRoleList = new ArrayList<>();

        //查找所有角色
        List<Role> roles = roleMapper.findAll();
        // 已选中的role
        List<Long> checkRoles = adminRoleMapper.findRoleById(id);

        for (Role role : roles) {
            if (checkRoles.contains(role.getId())){
                //代表选中的角色
                assginRoleList.add(role);
            }
            else {
                noAssginRoleList.add(role);
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("noAssginRoleList",noAssginRoleList);
        map.put("assginRoleList",assginRoleList);
        return map;
    }

    @Override
    public void addRole(Long adminId, Long[] roleIds) {
        //先删除所有角色
        adminRoleMapper.deleteByAdminId(adminId);
        //添加角色
        for (Long roleId : roleIds) {
            if (roleId !=null ){
                AdminRole adminRole = new AdminRole(roleId, adminId);
                adminRoleMapper.insert(adminRole);
            }
        }
    }

    @Override
    protected BaseMapper<Role> getEntityDao() {
        return roleMapper;
    }
}
