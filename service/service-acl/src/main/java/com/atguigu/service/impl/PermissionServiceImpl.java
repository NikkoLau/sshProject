package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Permission;
import com.atguigu.helper.PermissionHelper;
import com.atguigu.mapper.AdminRoleMapper;
import com.atguigu.mapper.BaseMapper;
import com.atguigu.mapper.PermissionMapper;
import com.atguigu.mapper.RolePermissionMapper;
import com.atguigu.servcie.Impl.BaseServiceImpl;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<Map<String,Object>> findAll(Long id) {
        List<Long> checkPermissions =  rolePermissionMapper.findAllByRoleId(id);
        List<Permission> permissions = permissionMapper.findAll();
        // var zNodes =[
        //     { id:1, pId:0, name:"随意勾选 1", open:true},
        ArrayList<Map<String ,Object>> zNodes = new ArrayList<>();
        for (Permission permission : permissions) {
            boolean flag = false;
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            if (checkPermissions !=null &&checkPermissions.contains(permission.getId())){
                flag =true;
            }
            map.put("checked",flag);
            zNodes.add(map);
        }
        return zNodes;
    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long id) {
        List<Permission> permissionList = null;
        if(id== 1L){
            permissionList =  permissionMapper.findAll();
        }else{
            permissionList =  rolePermissionMapper.findPermissByAdminId(id);
        }
        List<Permission> result = PermissionHelper.bulid(permissionList);
        return result;
    }

    @Override
    public List<Permission> findAllMenu() {
        List<Permission> permissionList = permissionMapper.findAll();
        if (permissionList!=null){
            List<Permission> bulid = PermissionHelper.bulid(permissionList);
            return bulid;
        }
        return null;
    }

    @Override
    protected BaseMapper<Permission> getEntityDao() {
        return permissionMapper;
    }
}
